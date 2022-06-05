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
            if (people[person]?.contains(contact) == true) {
                error("$contact already exists in the database!")
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
            if (people[person]?.contains(contact) == true) {
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
            if (people[person]?.contains(NumberPhone(numberPhone, phoneType)) == true) {
                error("${NumberPhone(numberPhone, phoneType)} already exists in the database!")
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
            if (people[person]?.contains(Email(login)) == true) {
                error("${Email(login)} already exists in the database!")
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
            if (people[person]?.contains(Link(link, socialNetworkType)) == true) {
                error("${Link(link, socialNetworkType)} already exists in the database!")
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
            if (people[person]?.contains(Address(city, street, house)) == true) {
                error("${Address(city, street, house)} already exists in the database!")
            } else {
                people.getOrPut(person) { mutableListOf() }.add(Address(city, street, house))
                logger.info("Add new ${Address(city, street, house)} to $person.")
            }
        }
    }

    override fun getPersonContacts(person: Person): List<ContactData> {
        logger.info("View all contacts of person: $person.")
        if (!people.containsKey(person)) error("$person could not be found in data base!")
        else return people[person]?.toList() ?: emptyList()
    }

    override fun getPersonPhones(person: Person): List<NumberPhone> {
        logger.info("View all numbers phone of person: $person.")
        if (!people.containsKey(person)) error("$person could not be found in data base!")
        else return people[person]?.filterIsInstance<NumberPhone>() ?: emptyList()
    }

    override fun getPersonEmails(person: Person): List<Email> {
        logger.info("View all emails of person: $person.")
        if (!people.containsKey(person)) error("$person could not be found in data base!")
        else return people[person]?.filterIsInstance<Email>() ?: emptyList()
    }

    override fun getPersonLinks(person: Person): List<Link> {
        logger.info("View all links of person: $person.")
        if (!people.containsKey(person)) error("$person could not be found in data base!")
        else return people[person]?.filterIsInstance<Link>() ?: emptyList()
    }

    override fun getPersonAddresses(person: Person): List<Address> {
        logger.info("View all addresses of person: $person.")
        if (!people.containsKey(person)) error("$person could not be found in data base!")
        else return people[person]?.filterIsInstance<Address>() ?: emptyList()
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