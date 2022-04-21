package lab3

sealed class ContactData
data class Person(
    val firstName: String,
    val lastName: String
) : ContactData() {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}

data class NumberPhone(
    val numberPhone: String,
    val type: PhoneType
) : ContactData() {
    override fun toString(): String {
        return "$type Phone: $numberPhone"
    }
}

data class Email(
    val login: String
) : ContactData() {
    override fun toString(): String {
        return "Email: $login"
    }
}

data class Link(
    val link: String,
    val type: SocialNetworkType
) : ContactData() {
    override fun toString(): String {
        return "Link $type: $link"
    }
}

data class Address(
    val city: String,
    val street: String,
    val house: String,
) : ContactData() {
    override fun toString(): String {
        return "Address: $city City, $street Street, $house"
    }
}


