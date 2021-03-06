package lab3

fun main() {
    val database = DataBase()

    val person1 = Person(firstName = "Duc", lastName = "Luong")

    database.run {

        addPhone(person1, "03669146821", PhoneType.HOME)
        removeContact(person1, NumberPhone("03669146821", PhoneType.HOME))
        addPhone(person1, "89112572532", PhoneType.MOBILE)
        addEmail(person1, "God.Luffy.008@gmail.com")
        addLink(person1, "https://www.facebook.com/duc.luong.325513800/", SocialNetworkType.FACEBOOK)
        addAddress(person1,"Thanh Hoa", "Le Loi","32A")
    }

    val person2 = Person(firstName = "Thao", lastName = "Linh")
    val phone2 = NumberPhone("03669146821", PhoneType.MOBILE)
    val email2 = Email("thaolinh2001@yahoo.com")
    val link2 = Link("vk.com/rinxtobi", SocialNetworkType.VK)
    val address2 = Address("Ha Noi","Hoang Dieu","137")

    database.run {

        addContact(person2, phone2)
        addContact(person2, email2)
        addContact(person2, link2)
        addContact(person2, address2)
    }

    println(database.getPersonContacts(person1))
    println(database.getPersonContacts(person2))

    println(database.getPersonPhones(person1))
    println(database.getPersonEmails(person1))
    println(database.getPersonLinks(person2))
    println(database.getPersonAddresses(person2))

    database.removePerson(person2)

    println(database.getAllContacts())
    println(database.getAllPersons())

    println(database.findPeopleBySubstring("uon"))

}