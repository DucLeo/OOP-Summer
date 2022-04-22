package lab3

import java.util.logging.Logger

val logger: Logger = Logger.getLogger(DataBase()::class.java.name)

class DataBase : ContactService {
    private val people: MutableMap<Person, MutableList<ContactData>> = mutableMapOf()

    override fun addContact(person: Person, contact: ContactData) {
        if (people[person] == null) {
            people.getOrPut(person) { mutableListOf() }.add(contact)
            logger.info("Add new $contact to $person.")
        } else {
            if (contact in people[person]!!.toList()) {

            } else {
                people.getOrPut(person) { mutableListOf() }.add(contact)
                logger.info("Add new $contact to $person.")
            }
        }
    }

    override fun removeContact(person: Person, contact: ContactData) {
        if (!people.containsKey(person)) error("$person could not be found in data base!")
        if (people[person] == null) {
            error("$person has not any data contact!")
        } else {
            if (contact in people[person]!!.toList()) {
                people.getOrPut(person) { mutableListOf() }.remove(contact)
                logger.info("Remove $contact of $person.")
            } else {
                error("$person has not this data contact!")
            }
        }
    }

    override fun removePerson(person: Person) {
        if (!people.containsKey(person)) error("$person could not be found in data base!")
        people.remove(person)
        logger.info("Remove all contacts of $person.")
    }

    override fun addPhone(person: Person, numberPhone: String, phoneType: PhoneType) {
        if (people[person] == null) {
            people.getOrPut(person) { mutableListOf() }.add(NumberPhone(numberPhone, phoneType))
            logger.info("Add new ${NumberPhone(numberPhone, phoneType)} to $person.")
        } else {
            if (NumberPhone(numberPhone, phoneType) in people[person]!!.toList()) {

            } else {
                people.getOrPut(person) { mutableListOf() }.add(NumberPhone(numberPhone, phoneType))
                logger.info("Add new ${NumberPhone(numberPhone, phoneType)} to $person.")
            }
        }
    }

    override fun addEmail(person: Person, login: String) {
        if (people[person] == null) {
            people.getOrPut(person) { mutableListOf() }.add(Email(login))
            logger.info("Add new ${Email(login)} to $person.")
        } else {
            if (Email(login) in people[person]!!.toList()) {

            } else {
                people.getOrPut(person) { mutableListOf() }.add(Email(login))
                logger.info("Add new ${Email(login)} to $person.")
            }
        }
    }

    override fun addLink(person: Person, link: String, socialNetworkType: SocialNetworkType) {
        if (people[person] == null) {
            people.getOrPut(person) { mutableListOf() }.add(Link(link, socialNetworkType))
            logger.info("Add new ${Link(link, socialNetworkType)} to $person.")
        } else {
            if (Link(link, socialNetworkType) in people[person]!!.toList()) {

            } else {
                people.getOrPut(person) { mutableListOf() }.add(Link(link, socialNetworkType))
                logger.info("Add new ${Link(link, socialNetworkType)} to $person.")
            }
        }
    }

    override fun addAddress(person: Person, city: String, street: String, house: String) {
        if (people[person] == null) {
            people.getOrPut(person) { mutableListOf() }.add(Address(city, street, house))
            logger.info("Add new ${Address(city, street, house)} to $person.")
        } else {
            if (Address(city, street, house) in people[person]!!.toList()) {

            } else {
                people.getOrPut(person) { mutableListOf() }.add(Address(city, street, house))
                logger.info("Add new ${Address(city, street, house)} to $person.")
            }
        }
    }

    override fun getPersonContacts(person: Person): List<ContactData> {
        if (!people.containsKey(person)) error("$person could not be found in data base!")
        logger.info("View all contacts of person: $person.")
        return if (people[person].isNullOrEmpty()) {
            mutableListOf<ContactData>()
        } else {
            people[person]!!.toList()
        }
    }

    override fun getPersonPhones(person: Person): List<NumberPhone> {
        if (!people.containsKey(person)) error("$person could not be found in data base!")
        logger.info("View all numbers phone of person: $person.")
        return if (people[person].isNullOrEmpty()) {
            mutableListOf<NumberPhone>()
        } else {
            people[person]!!.filterIsInstance<NumberPhone>()
        }
    }

    override fun getPersonEmails(person: Person): List<Email> {
        if (!people.containsKey(person)) error("$person could not be found in data base!")
        logger.info("View all emails of person: $person.")
        return if (people[person].isNullOrEmpty()) {
            mutableListOf<Email>()
        } else {
            people[person]!!.filterIsInstance<Email>()
        }
    }

    override fun getPersonLinks(person: Person): List<Link> {
        if (!people.containsKey(person)) error("$person could not be found in data base!")
        logger.info("View all links of person: $person.")
        return if (people[person].isNullOrEmpty()) {
            mutableListOf<Link>()
        } else {
            people[person]!!.filterIsInstance<Link>()
        }
    }

    override fun getPersonAddresses(person: Person): List<Address> {
        if (!people.containsKey(person)) error("$person could not be found in data base!")
        logger.info("View all addresses of person: $person.")
        return if (people[person].isNullOrEmpty()) {
            mutableListOf<Address>()
        } else {
            people[person]!!.filterIsInstance<Address>()
        }
    }

    override fun getAllPersons(): List<Person> {
        logger.info("View all people in data base.")
        return people.keys.toList()
    }

    override fun getAllContacts(): Map<Person, List<ContactData>> {
        logger.info("View all contacts in data base.")
        return people
    }

    override fun findPeopleBySubstring(substring: String): List<Person> {
        logger.info("Search for people by substring ($substring) in name.")
        return people.keys.filter { substring in it.firstName || substring in it.lastName }
    }
}