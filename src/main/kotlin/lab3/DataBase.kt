package lab3

class DataBase : ContactService {
    private val people: MutableMap<Person, MutableList<ContactData>> = mutableMapOf()

    override fun addContact(person: Person, contact: ContactData) {
        if (people[person] == null) {
            people.getOrPut(person) { mutableListOf() }.add(contact)
        } else {
            if (contact in people[person]!!.toList()) {

            } else {
                people.getOrPut(person) { mutableListOf() }.add(contact)
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
            } else {
                error("$person has not this data contact!")
            }
        }
    }

    override fun removePerson(person: Person) {
        if (!people.containsKey(person)) error("$person could not be found in data base!")
        people.remove(person)
    }

    override fun addPhone(person: Person, numberPhone: String, phoneType: PhoneType) {
        if (people[person] == null) {
            people.getOrPut(person) { mutableListOf() }.add(NumberPhone(numberPhone, phoneType))
        } else {
            if (NumberPhone(numberPhone, phoneType) in people[person]!!.toList()) {

            } else {
                people.getOrPut(person) { mutableListOf() }.add(NumberPhone(numberPhone, phoneType))
            }
        }
    }

    override fun addEmail(person: Person, login: String) {
        if (people[person] == null) {
            people.getOrPut(person) { mutableListOf() }.add(Email(login))
        } else {
            if (Email(login) in people[person]!!.toList()) {

            } else {
                people.getOrPut(person) { mutableListOf() }.add(Email(login))
            }
        }
    }

    override fun addLink(person: Person, link: String, socialNetworkType: SocialNetworkType) {
        if (people[person] == null) {
            people.getOrPut(person) { mutableListOf() }.add(Link(link, socialNetworkType))
        } else {
            if (Link(link, socialNetworkType) in people[person]!!.toList()) {

            } else {
                people.getOrPut(person) { mutableListOf() }.add(Link(link, socialNetworkType))
            }
        }
    }

    override fun addAddress(person: Person, city: String, street: String, house: String) {
        if (people[person] == null) {
            people.getOrPut(person) { mutableListOf() }.add(Address(city, street, house))
        } else {
            if (Address(city, street, house) in people[person]!!.toList()) {

            } else {
                people.getOrPut(person) { mutableListOf() }.add(Address(city, street, house))
            }
        }
    }

    override fun getPersonContacts(person: Person): List<ContactData> {
        return if (people[person].isNullOrEmpty()) {
            mutableListOf<ContactData>()
        } else {
            people[person]!!.toList()
        }
    }

    override fun getPersonPhones(person: Person): List<NumberPhone> {
        return if (people[person].isNullOrEmpty()) {
            mutableListOf<NumberPhone>()
        } else {
            people[person]!!.filterIsInstance<NumberPhone>()
        }
    }

    override fun getPersonEmails(person: Person): List<Email> {
        return if (people[person].isNullOrEmpty()) {
            mutableListOf<Email>()
        } else {
            people[person]!!.filterIsInstance<Email>()
        }
    }

    override fun getPersonLinks(person: Person): List<Link> {
        return if (people[person].isNullOrEmpty()) {
            mutableListOf<Link>()
        } else {
            people[person]!!.filterIsInstance<Link>()
        }
    }

    override fun getPersonAddresses(person: Person): List<Address> {
        return if (people[person].isNullOrEmpty()) {
            mutableListOf<Address>()
        } else {
            people[person]!!.filterIsInstance<Address>()
        }
    }

    override fun getAllPersons(): List<Person> {
        return people.keys.toList()
    }

    override fun getAllContacts(): Map<Person, List<ContactData>> {
        return people
    }

    override fun findPeopleBySubstring(substring: String): List<Person> {
        return people.keys.filter { substring in it.firstName || substring in it.lastName }
    }
}