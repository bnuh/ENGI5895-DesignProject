ENGI5895 Design Project
Swipe Weighted Twitter Randomizer
Bryan Melanson & Andre Blanco

To launch the code, first import the root folder into Android Studio as a new project using the "Import Project (Gradle, Eclipse ADT, etc.) option from the startup screen.

If the build option does not work at launch, use the Tools > Android > Sync Project with Gradle option to update with required dependencies.

At first launch the project will download posts into its database, but requres a hold signal before rendering the Cards. This is however not implemented, so no cards are displayed at first launch, run the program again and it will correctly poll the database before rendering the cards.

When swiping left, the database will update with a "viewed" value for the Tweets table, and the User rating will be updated (current rating/2)

When swiping right, the "viewed" value will be set, with no rating change.

To view these updates, the database can be seen in the "Device File Explorer" menu of Android Studio, in the device's "data/data/dependency.greendao.test.tinder.directional/databases" folder. From there, the db can be saved to the user's computer, and opened to see the results.