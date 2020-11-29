# DataFetcher

## Description: Developed an Android Application in Java using the MVVM and Repository pattern to fetch data from an API endpoint.

## Steps to run the project:
1. Clone the repository using the following command: 
```git clone https://github.com/Mk09878/DataFetcher.git```
2. Open AndroidStudio, Click on open and then select DataFetcher
3. In order to run the application just click on the green button on the top
4. In order to run the tests, navigate to the test folder and run the java file

## Project Structure:
The java directory consists of the following
1. The adapters directory consists of the CustomExpandableListAdapter class which acts as a bridge between an AdapterView and the underlying data for that view.
2. The api directory consists of ItemApi interface whose methods actually fetch data. However, concrete implementation of these methods is provided by retrofit
3. The models directory consists of two subfolders: Mixed and Service.
4. The repositories direcotry consists of 5 simulations which implement the first four tasks.
5. The utils directory consists of a simulation which implements the fifth task.
6. The viewmodels direcotry consists of helper classes which are required to run the simulations.
