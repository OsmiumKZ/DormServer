-- Accounts
SET IDENTITY_INSERT [accounts] ON
INSERT INTO [accounts] ([accounts].[id], [accounts].[login], [accounts].[password]) VALUES (1, 'root', 'root');
SET IDENTITY_INSERT [accounts] OFF

-- Names
SET IDENTITY_INSERT [names] ON
INSERT INTO [names] ([names].[id], [names].[name_ru], [names].[name_kz], [names].[name_en]) VALUES (1, 'Мужской', '', '');
INSERT INTO [names] ([names].[id], [names].[name_ru], [names].[name_kz], [names].[name_en]) VALUES (2, 'Женский', '', '');
INSERT INTO [names] ([names].[id], [names].[name_ru], [names].[name_kz], [names].[name_en]) VALUES (3, 'Ожидание', '', '');
INSERT INTO [names] ([names].[id], [names].[name_ru], [names].[name_kz], [names].[name_en]) VALUES (4, 'Заселен', '', '');
INSERT INTO [names] ([names].[id], [names].[name_ru], [names].[name_kz], [names].[name_en]) VALUES (5, 'Не заселен', '', '');
INSERT INTO [names] ([names].[id], [names].[name_ru], [names].[name_kz], [names].[name_en]) VALUES (6, 'Отказано', '', '');
INSERT INTO [names] ([names].[id], [names].[name_ru], [names].[name_kz], [names].[name_en]) VALUES (7, 'Академическая, 5/1', '', '');
INSERT INTO [names] ([names].[id], [names].[name_ru], [names].[name_kz], [names].[name_en]) VALUES (8, 'Академическая, 5', '', '');
INSERT INTO [names] ([names].[id], [names].[name_ru], [names].[name_kz], [names].[name_en]) VALUES (9, 'Комиссарова, 32', '', '');
INSERT INTO [names] ([names].[id], [names].[name_ru], [names].[name_kz], [names].[name_en]) VALUES (10, 'Грант МОН РК', '', '');
INSERT INTO [names] ([names].[id], [names].[name_ru], [names].[name_kz], [names].[name_en]) VALUES (11, 'Платное', '', '');
SET IDENTITY_INSERT [names] OFF

-- Genders
SET IDENTITY_INSERT [genders] ON
INSERT INTO [genders] ([genders].[id], [genders].[name_id]) VALUES (1, 1);
INSERT INTO [genders] ([genders].[id], [genders].[name_id]) VALUES (2, 2);
SET IDENTITY_INSERT [genders] OFF

-- Educational Form
SET IDENTITY_INSERT [educational_form] ON
INSERT INTO [educational_form] ([educational_form].[id], [educational_form].[name_id]) VALUES (1, 10);
INSERT INTO [educational_form] ([educational_form].[id], [educational_form].[name_id]) VALUES (2, 11);
SET IDENTITY_INSERT [educational_form] OFF

-- Status
SET IDENTITY_INSERT [status] ON
INSERT INTO [status] ([status].[id], [status].[name_id], [status].[active]) VALUES (1, 3, 0);
INSERT INTO [status] ([status].[id], [status].[name_id], [status].[active]) VALUES (2, 4, 1);
INSERT INTO [status] ([status].[id], [status].[name_id], [status].[active]) VALUES (3, 5, 0);
INSERT INTO [status] ([status].[id], [status].[name_id], [status].[active]) VALUES (4, 6, -1);
SET IDENTITY_INSERT [status] OFF

-- Dorms
SET IDENTITY_INSERT [dorms] ON
INSERT INTO [dorms] ([dorms].[id], [dorms].[name_id]) VALUES (1, 7);
INSERT INTO [dorms] ([dorms].[id], [dorms].[name_id]) VALUES (2, 8);
INSERT INTO [dorms] ([dorms].[id], [dorms].[name_id]) VALUES (3, 9);
SET IDENTITY_INSERT [dorms] OFF

-- Floors
SET IDENTITY_INSERT [floors] ON
INSERT INTO [floors] ([floors].[id], [floors].[number], [floors].[dorm_id]) VALUES (1, 1, 1);
INSERT INTO [floors] ([floors].[id], [floors].[number], [floors].[dorm_id]) VALUES (2, 2, 1);
INSERT INTO [floors] ([floors].[id], [floors].[number], [floors].[dorm_id]) VALUES (3, 3, 1);
INSERT INTO [floors] ([floors].[id], [floors].[number], [floors].[dorm_id]) VALUES (4, 3, 2);
INSERT INTO [floors] ([floors].[id], [floors].[number], [floors].[dorm_id]) VALUES (5, 1, 3);
INSERT INTO [floors] ([floors].[id], [floors].[number], [floors].[dorm_id]) VALUES (6, 2, 3);
INSERT INTO [floors] ([floors].[id], [floors].[number], [floors].[dorm_id]) VALUES (7, 3, 3);
SET IDENTITY_INSERT [floors] OFF

-- Rooms
SET IDENTITY_INSERT [rooms] ON
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (1, 101, 4, 1);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (2, 102, 4, 1);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (3, 103, 4, 1);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (4, 104, 4, 1);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (5, 105, 4, 1);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (6, 106, 4, 1);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (7, 107, 4, 1);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (8, 108, 4, 1);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (9, 109, 4, 1);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (10, 110, 4, 1);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (11, 201, 4, 2);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (12, 202, 4, 2);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (13, 203, 4, 2);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (14, 204, 4, 2);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (15, 205, 4, 2);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (16, 206, 4, 2);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (17, 207, 4, 2);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (18, 208, 4, 2);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (19, 209, 4, 2);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (20, 210, 4, 2);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (21, 301, 4, 3);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (22, 302, 4, 3);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (23, 303, 4, 3);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (24, 304, 4, 3);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (25, 305, 4, 3);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (26, 306, 4, 3);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (27, 307, 4, 3);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (28, 308, 4, 3);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (29, 309, 4, 3);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (30, 310, 4, 3);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (31, 303, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (32, 304, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (33, 305, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (34, 306, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (35, 307, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (36, 308, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (37, 309, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (38, 310, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (39, 311, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (40, 312, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (41, 314, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (42, 315, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (43, 316, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (44, 317, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (45, 318, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (46, 319, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (47, 320, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (48, 321, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (49, 322, 3, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (50, 324, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (51, 325, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (52, 326, 4, 4);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (53, 327, 4, 4);

INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (54, 3, 3, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (55, 4, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (56, 5, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (57, 6, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (58, 7, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (59, 8, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (60, 10, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (61, 19, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (62, 20, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (63, 21, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (64, 22, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (65, 23, 2, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (66, 25, 3, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (67, 26, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (68, 27, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (69, 28, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (70, 29, 3, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (71, 30, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (72, 31, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (73, 32, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (74, 33, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (75, 34, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (76, 35, 2, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (77, 36, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (78, 37, 2, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (79, 38, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (80, 39, 2, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (81, 40, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (82, 42, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (83, 43, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (84, 44, 5, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (85, 45, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (86, 46, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (87, 47, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (88, 48, 1, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (89, 49, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (90, 50, 2, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (91, 51, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[symbol], [rooms].[floor_id]) VALUES (92, 51, 1, 'А', 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[symbol], [rooms].[floor_id]) VALUES (93, 51, 1, 'Б', 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (94, 52, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (95, 53, 3, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (96, 54, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (97, 55, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (98, 56, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (99, 57, 3, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (100, 58, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (101, 59, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (102, 60, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (103, 61, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (104, 62, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (105, 63, 2, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (106, 64, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (107, 65, 2, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (108, 66, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (109, 67, 2, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (110, 68, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (111, 69, 2, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (112, 70, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (113, 71, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (114, 72, 5, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (115, 73, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (116, 74, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (117, 75, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (118, 76, 1, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (119, 77, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (120, 78, 2, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (121, 79, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[symbol], [rooms].[floor_id]) VALUES (122, 79, 1, 'А', 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[symbol], [rooms].[floor_id]) VALUES (123, 79, 1, 'Б',  7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (124, 80, 4,  7);
SET IDENTITY_INSERT [rooms] OFF