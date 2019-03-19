﻿-- Accounts
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
SET IDENTITY_INSERT [names] OFF

-- Genders
SET IDENTITY_INSERT [genders] ON
INSERT INTO [genders] ([genders].[id], [genders].[name_id]) VALUES (1, 1);
INSERT INTO [genders] ([genders].[id], [genders].[name_id]) VALUES (2, 2);
SET IDENTITY_INSERT [genders] OFF

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

INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (54, 1, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (55, 2, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (56, 3, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (57, 4, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (58, 5, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (59, 6, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (60, 7, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (61, 8, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (62, 9, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (63, 10, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (64, 11, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (65, 12, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (66, 13, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (67, 14, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (68, 15, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (69, 16, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (70, 17, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (71, 18, 4, 5);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (72, 1, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (73, 2, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (74, 3, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (75, 4, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (76, 5, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (77, 6, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (78, 7, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (79, 8, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (80, 9, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (81, 10, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (82, 11, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (83, 12, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (84, 13, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (85, 14, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (86, 15, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (87, 16, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (88, 17, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (89, 18, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (90, 19, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (91, 20, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (92, 21, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (93, 22, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (94, 23, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (95, 24, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (96, 25, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (97, 26, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (98, 27, 4, 6);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (99, 1, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (100, 2, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (101, 3, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (102, 4, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (103, 5, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (104, 6, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (105, 7, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (106, 8, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (107, 9, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (108, 10, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (109, 11, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (110, 12, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (111, 13, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (112, 14, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (113, 15, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (114, 16, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (115, 17, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (116, 18, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (117, 19, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (118, 20, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (119, 21, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (120, 22, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (121, 23, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (122, 24, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (123, 25, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (124, 26, 4, 7);
INSERT INTO [rooms] ([rooms].[id], [rooms].[number], [rooms].[max], [rooms].[floor_id]) VALUES (125, 32, 4, 7);
SET IDENTITY_INSERT [rooms] OFF