# itmo-term2
# BinarySearch
* класс BinarySearch реализовывает итеративный и рекурсивный варианты бинарного поиска в массиве.
* На вход подается целое число x и массив целых чисел a, отсортированный по невозрастанию. На выходе минимальное значение индекса i, при котором a[i] <= x.
* Для main, функций бинарного поиска и вспомогательных функций указаны, пред- и постусловия. Для реализаций методов приведены доказательства соблюдения контрактов в терминах троек Хоара.
* Интерфейс программы.
* Имя основного класса — search.BinarySearch.
* Первый аргумент командной строки — число x.
* Последующие аргументы командной строки — элементы массива a.
* Пример запуска: 
* javac search.BinarySearch.java
* java search.BinarySearch 3 5 4 3 2 1. Ожидаемый результат: 2.
# BinarySearchMax
* На вход подается массив полученный приписыванием отсортированного (строго) по убыванию массива в конец массива отсортированного (строго) по возрастанию
* Ответ максимальное значение в нем