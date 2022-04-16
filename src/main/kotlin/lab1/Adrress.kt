package lab1

data class Address(
    val index: String,
    val city: String,
    val street: String,
    val house: Int
) {
    override fun toString(): String {
        return ("$index, $city, ул. $street, д. $house")
    }
}

fun parseListAddresses(TextListAddresses: String): MutableList<Address> {
    val listAddresses = mutableListOf<Address>()

    if (TextListAddresses.isNotEmpty()) {
        val addresses = TextListAddresses.split('\n')

        for (one in addresses) {
            val parameter = one.split(", ")
            val numberIndex = parameter[0].split(". ")
            val index = numberIndex[1]
            val city = parameter[1]
            val street = parameter[2].drop(4)
            val house = parameter[3].drop(3).toInt()
            val newAddress = Address(index, city, street, house)

            listAddresses.add(newAddress)
        }
    }

    return listAddresses
}

fun smallestIndex(listAddresses: List<Address>): Address? {
    return if (listAddresses.isEmpty()) {
        null
    } else {
        var requiredAddress = listAddresses[0]

        for (Address in listAddresses) {
            if (requiredAddress.index.toInt() > Address.index.toInt())
                requiredAddress = Address
        }

        requiredAddress
    }
}

fun biggestIndex(listAddresses: List<Address>): Address? {
    return if (listAddresses.isEmpty()) {
        null
    } else {
        var requiredAddress = listAddresses[0]

        for (Address in listAddresses) {
            if (requiredAddress.index.toInt() < Address.index.toInt())
                requiredAddress = Address
        }

        requiredAddress
    }
}


fun shortestNameStreet(listAddresses: List<Address>): Address? {
    return if (listAddresses.isEmpty()) {
        null
    } else {
        var requiredAddress = listAddresses[0]

        for (Address in listAddresses) {
            if (requiredAddress.street.length > Address.street.length)
                requiredAddress = Address
        }

        requiredAddress
    }
}

fun longestNameStreet(listAddresses: List<Address>): Address? {
    return if (listAddresses.isEmpty()) {
        null
    } else {
        var requiredAddress = listAddresses[0]

        for (Address in listAddresses) {
            if (requiredAddress.street.length < Address.street.length)
                requiredAddress = Address
        }

        requiredAddress
    }
}
