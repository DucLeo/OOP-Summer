package lab3

import lab3.ContactData.*

interface ContactService {
    fun addContact(person: Person, contact: ContactData)
    fun removeContact(person: Person, contact: ContactData)

    fun removePerson(person: Person)

    fun addPhone(person: Person, phone: String, phoneType: PhoneType)
    fun addEmail(person: Person, email: String)
    fun addLink(person: Person, link: String, socialNetworkType: SocialNetworkType)
    fun addAddress(person: Person, city: String, street: String, house: String)

    fun getPersonContacts(person: Person): List<ContactData>
    fun getPersonPhones(person: Person): List<NumberPhone>
    fun getPersonEmails(person: Person): List<Email>
    fun getPersonLinks(person: Person): List<Link>
    fun getPersonAddresses(person: Person): List<Address>

    fun getAllPersons(): List<Person>
    fun getAllContacts(): Map<Person, List<ContactData>>

    fun findPeopleBySubstring(substring: String): List<Person>
}