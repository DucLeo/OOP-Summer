import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertDoesNotThrow
import lab1.*

internal class AddressKtTest {
    @Test
    fun test_parseListAddresses() {
        var address = ""

        assertEquals(parseListAddresses(address), emptyList<Address>())

        address = """
        1. 091005, Санкт-Петербург, ул. Балтийская, д. 10
        2. 100820, Москва, ул. Арбата, д. 8
        """.trimIndent()

        val listAddresses = parseListAddresses(address)

        assertEquals(listAddresses[0].index, "091005")
        assertEquals(listAddresses[1].index, "100820")
        assertEquals(listAddresses[0].city, "Санкт-Петербург")
        assertEquals(listAddresses[1].city, "Москва")
        assertEquals(listAddresses[0].street, "Балтийская")
        assertEquals(listAddresses[1].street, "Арбата")
        assertEquals(listAddresses[0].house, 10)
        assertEquals(listAddresses[1].house, 8)

    }

    @Test
    fun test_smallestIndex() {
        var listAddresses: List<Address> = emptyList()

        assertEquals(smallestIndex(listAddresses), null)

        listAddresses = listOf(
            Address("091005", "Санкт-Петербург", "Балтийская", 10),
            Address("100820", "Москва", "Арбата", 8),
        )
        val address = smallestIndex(listAddresses)
        assertDoesNotThrow {
            smallestIndex(listAddresses)
        }
        assertEquals(address, listAddresses.minByOrNull { it.index.toInt() })
    }

    @Test
    fun test_biggestIndex() {
        var listAddresses: List<Address> = emptyList()

        assertEquals(biggestIndex(listAddresses), null)

        listAddresses = listOf(
            Address("091005", "Санкт-Петербург", "Балтийская", 10),
            Address("100820", "Москва", "Арбата", 8),
        )
        val address = biggestIndex(listAddresses)
        assertDoesNotThrow {
            biggestIndex(listAddresses)
        }
        assertEquals(address, listAddresses.maxByOrNull { it.index.toInt() })
    }

    @Test
    fun test_shortestNameStreet() {
        var listAddresses: List<Address> = emptyList()

        assertEquals(shortestNameStreet(listAddresses), null)

        listAddresses = listOf(
            Address("091005", "Санкт-Петербург", "Балтийская", 10),
            Address("100820", "Москва", "Арбата", 8),
        )
        val address = shortestNameStreet(listAddresses)
        assertDoesNotThrow {
            shortestNameStreet(listAddresses)
        }
        assertEquals(address, listAddresses.minByOrNull { it.street.length })
    }

    @Test
    fun test_longestNameStreet() {
        var listAddresses: List<Address> = emptyList()

        assertEquals(longestNameStreet(listAddresses), null)

        listAddresses = listOf(
            Address("091005", "Санкт-Петербург", "Балтийская", 10),
            Address("100820", "Москва", "Арбата", 8),
        )
        val address = longestNameStreet(listAddresses)
        assertDoesNotThrow {
            longestNameStreet(listAddresses)
        }
        assertEquals(address, listAddresses.maxByOrNull { it.street.length })
    }
}