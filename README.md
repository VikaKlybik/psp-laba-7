# psp-laba-7
 Вариант 7
## Часть 1

 Разработать приложение управления базой данных. Предусмотреть исключительные ситуации (ввод текста вместо числа, корректная обработка даты, повторение первичного ключа при вводе новых данных). 
	Предметная область: больница. Сущность:
-  пациенты – фамилия, имя, отчество, дата рождения, диагноз; 

## Часть 2

 В каждом из заданий необходимо выполнить следующие действия:
•	Организацию соединения с базой данных вынести в отдельный класс, метод которого возвращает соединение.

•	Создать БД с необходимыми таблицами. 

•	Создать класс для выполнения запросов на извлечение информации из БД с использованием компилированных запросов.

•	Создать класс на добавление информации.

•	Создать JSP- страницу с полями для формирования запроса.

•	Результаты выполнения запроса передать клиенту в виде JSP-страницы.


Продукция. В БД хранится информация о продукции компании.

Для продукции необходимо хранить:
	– название;
 
	– группу продукции (телефоны, телевизоры и др.);
 
	– описание;
 
	– дату выпуска;
 
	– значения параметров.
 
Для групп продукции необходимо хранить:
	– название;
 
	– перечень групп параметров (размеры и др.).
 
Для групп параметров необходимо хранить:

	– название;
 
	– перечень параметров.
 
Для параметров необходимо хранить:

	– название;
 
	– единицу измерения.
