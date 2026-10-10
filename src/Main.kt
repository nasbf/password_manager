//Create a basic manager passwords
import java.io.File

 fun main(){
     val file = File("data.txt")
     file.createNewFile()

     //Create an empty and mutable list that content objects of the Data class
     val dataList = mutableListOf<Data>()

     //Store our data in a content variable,
     // then use lines method to separate the lines converting content in a list of text

     val content = file.readText()
     val lines = content.lines()
     //println(lines)


     // using a for create an accountData variable where use split method and store each part of an account
     for (line in lines){
         if (line.isNotBlank()){
             val accountData = line.split(",")
             val newAccount= Data(
                 website = accountData[0],
                 user = accountData[1],
                 password = accountData[2]
             )

             dataList.add(newAccount)
         }

     }
     println("Accounts loaded: ${dataList.size}")

     for (account in dataList) {
         println("${account.website} - ${account.user} - ${account.password}")
     }


    var userOption: String

    //Using do while loop to display the menu and develop all options
    do {
        println("\n**\nEnter an option:\n\n1. View all accounts\n2. Add Data\n3. Find the website password\n4. Change Password\n5. Exit\n**")
        userOption = readln()
        when (userOption) {
            "1" -> {
                println("your accounts available: ")
                for (x in dataList)
                    println("${x.website}, ${x.user}, ${x.password}")
            }

            "2" -> {
                println("type your new account, first your new site: ")
                val website = readln()
                println("Now type your user: ")
                val user = readln()
                println("And finally type your password: ")
                val password = readln()
                //using add method, add the new account to dataList, then using appendText add this new data to our txt file
                dataList.add(Data(website = website, user = user, password = password))
                file.appendText("\n$website,$user,$password")
                println("_____________")
                println("Account added successfully\n\nWebsite: $website\nUser: $user\nPassword: $password")
                println("_____________")
            }


            "3" -> {
                val data = websitesList(dataList)
                if (data != null) {
                    println("Your password for ${data.website} is: ${data.password}")
                } else {
                    println("website not fount")
                }

            }


            "4" -> {
                val data = websitesList(dataList)
                if (data != null) {
                    println("the data for ${data.website} are:\nUser: ${data.user}\nPassword: ${data.password}\nType your new password:__ ")
                    data.password = readln()
                    val accounts = dataList.joinToString("\n") {
                       account -> "${account.website},${account.user},${account.password}"
                    }
                    file.writeText(accounts)

                    //println("\nYour password is updated:\nWebsite: ${data.website} \nUser: ${data.user}\nPassword: ${data.password}")
                    //file.appendText("")
                } else {
                    println("Website not found")
                }
            }

            "5" -> println("See you soon!!")
        }
    }
        while (userOption != "5")
 }

/*
Create a function to avoid code duplication. websitlist function to display the list of websites available in the dataList
and offer the option to select one of them; finally, the user's choice will be returned as an object for further use, if the
data is null return null.
*/

fun websitesList (dataList:List<Data>): Data?{
    println("Here the list of websites available select one")
    for (x in dataList) {

        println(x.website)

    }
    println("\nEnter a website: ___")
    val userWebsite = readln()

    for (x in dataList) {
        if (x.website == userWebsite) {
            return x

        }
    }
    return null
}





