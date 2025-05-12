# FinSight

## Общие сведения
FinSight — веб-приложение для мониторинга финансовых операций и генерации отчётов. Пользователи могут записывать доходы и расходы, просматривать историю транзакций и получать аналитические отчёты на основе накопленных данных.

## Содержание
1. [Возможности](#возможности)
2. [Технологический стек](#технологический-стек)
3. [Архитектура](#архитектура)
4. [Схема базы данных](#схема-базы-данных)
5. [API эндпоинты](#api-эндпоинты)
6. [Установка](#установка)
7. [Использование](#использование)

## Возможности
- **Аутентификация**: HTTP Basic Auth при каждом запросе, быстрая регистрация с созданием основного счёта.  
- **Профиль**: просмотр и обновление данных профиля (имя, email, телефон, ИНН).  
- **Управление счетами**: создание, обновление и удаление банковских счетов; привязка к справочнику банков.  
- **Контрагенты**: полный CRUD контрагентов — физлиц и организаций с учётом типа, ИНН, реквизитов и даты создания.  
- **Транзакции**:
  - Создание, обновление (только для транзакций со статусом **NEW**) и логическое удаление (пометка **DELETED**) операций.
  - Многоуровневая фильтрация и сортировка: по банкам отправителя/получателя, датам (диапазон и конкретная дата), статусам, типам, ИНН контрагентов, сумме (min/max) и категориям.
  - Пагинация и сортировка результатов для удобного просмотра больших объёмов данных.
- **Справочники**: 
  - Получение списков типов сущностей (физлицо/юрлицо), типов транзакций и их статусов без авторизации.
  - Динамический справочник банков и категорий, с возможностью фильтрации категорий по типу сущности.
- **Отчёты**:
  - Генерация отчётов по заданным фильтрам в форматах **PDF**, **Excel** и **JSON**.
  - Структурированные ответы с детализацией транзакций и статистикой: по периодам, типам, статусам, банкам и категориям.

## Технологический стек
- **Backend**: REST API на Java spring
- **Аутентификация**: HTTP Basic Auth  
- **База данных**: PostgreSQL  
- **Frontend**: React  

## Архитектура
FinSight реализует клиент-серверную модель:
- **REST API**: отвечает за аутентификацию, операции с транзакциями, управление пользователями и аналитические данные.
- **React Frontend**: обеспечивает адаптивный пользовательский интерфейс для ввода данных, визуализации и экспорта отчётов.
- **PostgreSQL**: хранит пользователей, счета, контрагентов, транзакции, категории, банки и справочные данные.

## Схема базы данных

- **entity_types**
  - `id` SERIAL PRIMARY KEY — уникальный идентификатор типа сущности.
  - `name` VARCHAR(50) NOT NULL UNIQUE — название типа (например, «физлицо», «юрлицо»).

- **transaction_types**
  - `id` SERIAL PRIMARY KEY — идентификатор типа операции.
  - `name` VARCHAR(50) NOT NULL UNIQUE — наименование типа (например, «поступление», «списание»).

- **transaction_statuses**
  - `id` SERIAL PRIMARY KEY — идентификатор статуса операции.
  - `name` VARCHAR(50) NOT NULL UNIQUE — наименование статуса (например, «новая», «подтверждённая», «в обработке», «отменена», «выполнена», «удалена»).

- **users**
  - `id` SERIAL PRIMARY KEY — уникальный идентификатор пользователя.
  - `email` VARCHAR(100) NOT NULL UNIQUE — электронная почта.
  - `password` VARCHAR(255) NOT NULL — хеш пароля.
  - `name` VARCHAR(50) NOT NULL — имя пользователя.
  - `phone_number` VARCHAR(20) NOT NULL UNIQUE — номер телефона.
  - `tin` VARCHAR(12) NOT NULL UNIQUE — ИНН пользователя.

- **banks**
  - `id` SERIAL PRIMARY KEY — уникальный идентификатор банка.
  - `name` VARCHAR(255) NOT NULL UNIQUE — наименование банка.

- **accounts**
  - `id` SERIAL PRIMARY KEY — идентификатор счёта.
  - `user_id` INT NOT NULL REFERENCES users(id) — ссылка на владельца счёта.
  - `account_number` VARCHAR(50) NOT NULL UNIQUE — номер банковского счёта.
  - `bank_id` INT NOT NULL REFERENCES banks(id) — ссылка на банк.

- **counterparties**
  - `id` SERIAL PRIMARY KEY — идентификатор контрагента.
  - `user_id` INT NOT NULL REFERENCES users(id) — владелец, создавший контрагента.
  - `name` VARCHAR(255) NOT NULL — название организации или имя человека.
  - `phone_number` VARCHAR(20) NOT NULL UNIQUE — контактный телефон.
  - `entity_type_id` INT NOT NULL REFERENCES entity_types(id) — тип контрагента.
  - `tin` VARCHAR(12) NOT NULL UNIQUE — ИНН контрагента.
  - `account_number` VARCHAR(50) NOT NULL UNIQUE — счёт контрагента.
  - `bank_id` INT NOT NULL REFERENCES banks(id) — банк контрагента.
  - `created_at` TIMESTAMPTZ NOT NULL DEFAULT now() — дата и время создания записи.

- **categories**
  - `id` SERIAL PRIMARY KEY — идентификатор категории.
  - `entity_type_id` INT NOT NULL REFERENCES entity_types(id) — тип лица (физлицо/юрлицо) для категории.
  - `name` VARCHAR(50) NOT NULL UNIQUE — наименование категории (например, «продукты», «транспорт»).

- **transactions**
  - `id` SERIAL PRIMARY KEY — идентификатор транзакции.
  - `date_time` TIMESTAMPTZ NOT NULL — дата и время операции.
  - `transaction_type_id` INT NOT NULL REFERENCES transaction_types(id) — тип операции.
  - `transaction_status_id` INT NOT NULL REFERENCES transaction_statuses(id) — статус операции.
  - `amount` DECIMAL(15,5) NOT NULL — сумма операции.
  - `comment` VARCHAR(255) — комментарий к транзакции.
  - `account_id` INT NOT NULL REFERENCES accounts(id) — счёт пользователя (отправитель или получатель, смотря по флагу).
  - `counterparty_id` INT NOT NULL REFERENCES counterparties(id) — контрагент операции.
  - `user_is_sender` BOOLEAN NOT NULL — флаг, указывающий направление (true — пользователь отправляет, false — получает).
  - `category_id` INT NOT NULL REFERENCES categories(id) — категория для аналитики.

## API эндпоинты
- **Регистрация**: `POST /register` — регистрация пользователя с созданием основного счёта.
- **Справочники (без авторизации)**:
  - `GET /entity_types` — типы сущностей.
  - `GET /transaction_types` — типы транзакций.
  - `GET /transaction_statuses` — статусы транзакций.
  - `GET /banks` — список банков.
  - `GET /categories?entityTypeId={id}` — категории по типу сущности.
- **Профиль**:
  - `GET /user/profile` — получить данные профиля.
  - `PUT /user/profile` — обновить данные профиля.
- **Счета**:
  - `GET /user/accounts` — список счетов.
  - `POST /user/accounts` — создать счёт.
  - `PUT /user/accounts/{id}` — обновить счёт.
  - `DELETE /user/accounts/{id}` — отвязать счёт.
- **Контрагенты**:
  - `GET /user/counterparties` — список контрагентов.
  - `POST /user/counterparties` — создать контрагента.
  - `GET /user/counterparties/{id}` — получить контрагента.
  - `PUT /user/counterparties/{id}` — обновить контрагента.
  - `DELETE /user/counterparties/{id}` — удалить контрагента.
- **Транзакции**:
  - `GET /user/transactions` — список транзакций с поддержкой пагинации, сортировки и фильтрации.
  - `POST /user/transactions` — создать транзакцию.
  - `PUT /user/transactions/{id}` — обновить транзакцию (только NEW).
  - `DELETE /user/transactions/{id}` — пометить транзакцию как DELETED.
- **Отчёты**:
  - `GET /user/reports?format={pdf|excel|json}&…` — экспорт отчётов с фильтрами.

## Установка
1. Клонировать репозиторий:
   ```bash
   git clone https://github.com/yourusername/FinSight.git
   cd FinSight
   ```
2. **Backend**:
   - Настроить PostgreSQL и обновить `application.properties` с учётными данными БД.
   - Собрать и запустить:
     ```bash
     mvn clean install
     java -jar target/finsight-api.jar
     ```
3. **Frontend**:
   - Перейти в папку `frontend`.
   - Установить зависимости и запустить:
     ```bash
     npm install
     npm start
     ```

## Использование
1. Зарегистрироваться и войти в систему.
2. Добавить дополнительные банковские счета в разделе «Счета» (при необходимости).
3. Добавить контрагентов.
4. Записывать транзакции и назначать категории.
5. Анализировать данные с помощью фильтров.

