package lab1

fun main() {
    val textListAddresses = """
        1. 091005, Санкт-Петербург, ул. Балтийская, д. 10
        2. 100820, Москва, ул. Арбата, д. 8
        3. 120720, Москва, ул. Волхонка, д. 24
        4. 190577, Санкт-Петербург, ул. Английская набережная, д. 17
        5. 002051, Казань, ул. Беломорская, д. 25
        6. 156311, Санкт-Петербург, ул. Чайковского, д. 14
        7. 123456, Омск, ул. Конева, д. 35
        8. 105443, Санкт-Петербург, ул. Студенческая, д. 10
        9. 100121, Омск, ул. Фрунзе, д. 5
        10. 095528, Казань, ул. Боевая, д. 31
        """.trimIndent()

    val listAddresses: List<Address>

    listAddresses = parseListAddresses(textListAddresses)

    println("List of addresses:")
    for (i in 1..listAddresses.size) {
        print("$i. ")
        println(listAddresses[i - 1])
    }

    println("\nAddress with the smallest index: ${smallestIndex(listAddresses)}")
    println("Address with the biggest  index: ${biggestIndex(listAddresses)}")
    println("\nAddress with the shortest name of street: ${shortestNameStreet(listAddresses)}")
    println("Address with the longest  name of street: ${longestNameStreet(listAddresses)}")
}