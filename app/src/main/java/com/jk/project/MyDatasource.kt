package com.jk.project

class MyDatasource {
    var classArrayList: ArrayList<Class> = ArrayList()


    // create a private constructor
    private constructor() {
        classArrayList.add(
            Class(
                1,
                "Learn HTML & CSS",
                "Length: 1 hr 58 min",
                R.drawable.one,
                false,
                "This module provides an in-depth look at the fundamentals of coding in HTML and CSS. We start by exploring HTML Development using some of the most commonly used tags and attributes. " +
                        "Then we will dive into CSS to fully customize the look of their web pages.",
                "https://www.youtube.com/watch?v=gXLjWRteuWI&ab_channel=DesignCourse",
                null
            )
        )
        classArrayList.add(
            Class(
                2,
                "What is Javascript?",
                "Length: 48 min",
                R.drawable.two,
                false,
                "JavaScript is one of the most popular programming languages. A lot of people are learning JavaScript to become front-end and/or back-end developers. " +
                        "We'll start off by setting up our development environment and start coding.",
                "https://www.youtube.com/watch?v=W6NZfCO5SIk&ab_channel=ProgrammingwithMosh",
                null
            )
        )
        classArrayList.add(
            Class(
                3,
                "Variables and Conditions",
                "Length: 15 min",
                R.drawable.three,
                false,
                "In this module, you will learn about variables and conditions in Javascript. Variables are important because they allow you to programmatically store data. Conditions are useful for making choices.",
                "https://www.youtube.com/watch?v=edlFjlzxkSI&ab_channel=developedbyed",
                null
            )
        )
        classArrayList.add(
            Class(
                4,
                "Loops",
                "Length: 10 min",
                R.drawable.four,
                false,
                "We'll learn about all of the different types of loops in JavaScript. Loops are a way of repeating things in JavaScript. " +
                        "We'll cover FOR, FOR..IN, FOR..OF, WHILE, DO..WHILE, and the high order array function forEach.",
                "https://www.youtube.com/watch?v=Kn06785pkJg&ab_channel=codeSTACKr",
                null
            )
        )
    }

    companion object {
        @Volatile
        private lateinit var instance: MyDatasource

        fun getInstance(): MyDatasource {
            synchronized(this) {
                if (!::instance.isInitialized) {
                    instance = MyDatasource()
                }
                return instance
            }
        }
    }

    // put the data you want to store in the singleton here
    var userName: String? = null
//    lateinit var classArray : ArrayList<Class>


}
