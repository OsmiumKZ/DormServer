-- --------------------------------------------------------

--
-- Структура таблицы "название"
--
-- [id] - ID названия
-- [name_ru] - на русском
-- [name_kz] - на казахском
-- [name_en] - на английском
--

create TABLE [names]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name_ru] NVARCHAR(150) NOT NULL,
     [name_kz] NVARCHAR(150) NULL,
     [name_en] NVARCHAR(150) NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "общежитие"
--
-- [id] - ID общаги
-- [name_id] - Название общежития
--

create TABLE [dorms]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name_id] INT NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "комната"
--
-- [id] - ID комнаты
-- [number] - Нумерация комнаты
-- [max] - Максимальное количество людей в комнате
-- [symbol] - Символ(-ы) комнаты
-- [floor_id] - ID этажа
--

create TABLE [rooms]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [number] INT NOT NULL,
     [max] INT NOT NULL,
     [symbol] NVARCHAR(5) NULL,
     [floor_id] INT NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "гендер"
--
-- [id] - ID пола
-- [name_id] - Название пола
--
create TABLE [genders]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name_id] INT NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "отчет"
--
-- [id] - ID отчета.
-- [gender_id] - ID пола.
-- [room_id] - ID комнаты.
-- [status_id] - ID статуса.
-- [date_create] - Дата создания отчета.
-- [date_update] - Дата обновление отчета.
-- [email] - Электронная почта.
-- [residence_permit] - ID вида на жительство.
-- [phone] - Телефон.
-- [children] - Сколько в семье детей.
-- [date_residence] - Дата начала проживания.
-- [shelter_id] - ID приюта
-- [name_f] - Имя.
-- [name_l] - Фамилия.
-- [patronymic] - Отчество.
-- [educational_form_id] - ID формы обучения.
-- [group] - Группа.
-- [citizenship_id] - ID гражданства.
--
create TABLE [reports]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [gender_id] INT NOT NULL,
     [room_id] INT NOT NULL,
     [status_id] INT NOT NULL,
     [date_create] DATETIME NOT NULL,
     [date_update] DATETIME NOT NULL,
     [email] NVARCHAR(254) NULL,
     [residence_permit_id] INT NOT NULL,
     [phone] NVARCHAR(15) NOT NULL,
     [children] INT NOT NULL,
     [date_residence] DATE NOT NULL,
     [shelter_id] INT NOT NULL,
     [name_f_id] NVARCHAR(40) NOT NULL,
     [name_l_id] NVARCHAR(40) NOT NULL,
     [patronymic_id] NVARCHAR(40) NULL,
     [group] NVARCHAR(10) NOT NULL,
     [citizenship_id] INT NOT NULL,
     [educational_form_id] INT NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "этаж"
--
-- [id] - ID этажа
-- [number] - Номер этажа
-- [number] - ID общаги
--

create TABLE [floors]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [number] INT NOT NULL,
     [dorm_id] INT NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "статус"
--
-- [id] - ID статуса
-- [name_id] - Название статуса
-- [active] - (0 - свободно), (1 - занято) место. (-1) отказ (после принятия заявления).
--

create TABLE [status]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name_id] INT NOT NULL,
     [active] INT NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "аккаунт"
--
-- [id] - ID статуса
-- [login] - Логин
-- [password] - Пароль
--

create TABLE [accounts]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [login] NVARCHAR(40) NOT NULL,
     [password] NVARCHAR(40) NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "заявление"
--
-- [id] - ID заявления
-- [name_f] - Имя
-- [name_l] - Фамилия
-- [patronymic] - Отчество
-- [email] - Электронная почта
-- [residence_permit] - ID вида на жительство
-- [phone] - Телефон
-- [group] - Группа
-- [room_id] - ID комнаты
-- [gender_id] - ID пола
-- [shelter_id] - ID приюта
-- [children] - Сколько в семье детей.
-- [date_residence] - Дата начала проживания.
-- [date_create] - Дата создания.
-- [active] - (0 - открыт) и (1 - закрыт). Или же (0 - не прочтен) и (1 - прочтен).
-- [educational_form_id] - ID формы обучения.
-- [citizenship_id] - ID гражданства.
--

create TABLE [requests]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name_f_id] NVARCHAR(40) NOT NULL,
     [name_l_id] NVARCHAR(40) NOT NULL,
     [patronymic_id] NVARCHAR(40) NULL,
     [email] NVARCHAR(254) NULL,
     [residence_permit_id] INT NOT NULL,
     [phone] NVARCHAR(15) NOT NULL,
     [group] NVARCHAR(10) NOT NULL,
     [room_id] INT NOT NULL,
     [gender_id] INT NOT NULL,
     [shelter_id] INT NOT NULL,
     [children] INT NOT NULL,
     [date_residence] DATE NOT NULL,
     [date_create] DATETIME NOT NULL,
     [active] INT NOT NULL,
     [citizenship_id] INT NOT NULL,
     [educational_form_id] INT NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "Родитель"
--
-- [id] - ID родителя
-- [name_f_id] - Имя
-- [name_l_id] - Фамилия
-- [patronymic_id] - Отчество
-- [phone] - Телефон
--

create TABLE [parents]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name_f_id] INT NOT NULL,
     [name_l_id] INT NOT NULL,
     [patronymic_id] INT NULL,
     [phone] NVARCHAR(15) NOT NULL)
GO

-- --------------------------------------------------------

--
-- Структура таблицы "Опекун"
--
-- [id] - ID опекуна
-- [name_f_id] - Имя
-- [name_l_id] - Фамилия
-- [patronymic_id] - Отчество
-- [phone] - Телефон
--

create TABLE [guardians]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name_f_id] INT NOT NULL,
     [name_l_id] INT NOT NULL,
     [patronymic_id] INT NULL,
     [phone] NVARCHAR(15) NOT NULL)
GO

-- --------------------------------------------------------

--
-- Структура таблицы "Детский дом"
--
-- [id] - ID детского дома.
-- [address] - Адрес
-- [phone] - Телефон
--

create TABLE [orphanages]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [address] NVARCHAR(140) NOT NULL,
     [phone] NVARCHAR(15) NOT NULL)
GO

-- --------------------------------------------------------

--
-- Структура таблицы "Приют"
--
-- [id] - ID детского дома.
-- [parent_mother_id] - ID мамы.
-- [parent_father_id] - ID папы.
-- [guardian_id] - ID опекуна.
-- [orphanage_id] - ID детского дома.
--

create TABLE [shelters]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [parent_mother_id] INT NULL,
     [parent_father_id] INT NULL,
     [guardian_id] INT NULL,
     [orphanage_id] INT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "Имя"
--
-- [id] - ID имени
-- [name] - Имя
--

create TABLE [name_f]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name] NVARCHAR(40) NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "Фамилия"
--
-- [id] - ID фамилии
-- [name] - Фамилия
--

create TABLE [name_l]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name] NVARCHAR(40) NOT NULL)
GO
	
-- --------------------------------------------------------

--
-- Структура таблицы "Отчество"
--
-- [id] - ID отчества
-- [name] - Отчество
--

create TABLE [patronymic]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name] NVARCHAR(40) NOT NULL)
GO

-- --------------------------------------------------------

--
-- Структура таблицы "Форма обучения"
--
-- [id] - ID формы обучения.
-- [name_id] - ID Название
--

create TABLE [educational_form]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name_id] INT NOT NULL)
GO

-- --------------------------------------------------------

--
-- Структура таблицы "Город"
--
-- [id] - ID города.
-- [name] - Город
--

create TABLE [cities]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name] NVARCHAR(40) NOT NULL)
GO

-- --------------------------------------------------------

--
-- Структура таблицы "Страна"
--
-- [id] - ID страны.
-- [name] - Страна
--

create TABLE [countries]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [name] NVARCHAR(40) NOT NULL)
GO

-- --------------------------------------------------------

--
-- Структура таблицы "Вид на жительство"
--
-- [id] - ID вида на жительства.
-- [city_id] - ID города
-- [country_id] - ID страны
-- [address] - Адрес
--

create TABLE [residence_permit]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [city_id] INT NOT NULL,
     [country_id] INT NOT NULL,
     [address] NVARCHAR(60) NOT NULL)
GO

-- --------------------------------------------------------

--
-- Структура таблицы "Гражданство"
--
-- [id] - ID гражданства.
-- [country_id] - ID страны.
-- [number] - номер (ИИН, номер паспорта и т.п.).
--

create TABLE [citizenships]
    ([id] INT IDENTITY (1, 1) PRIMARY KEY,
     [country_id] INT NOT NULL,
     [number] NVARCHAR(20) NOT NULL)
GO
