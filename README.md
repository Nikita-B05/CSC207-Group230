<h1 align="center" style="margin-bottom: 0em;">Financial Literacy Game</h1>
<h3 align="center" style="margin-top: 0; margin-bottom: 1.5em">CSC207-Group230</h3>

---

## Table of Contents

1. [Team Members](#team-members)
2. [Project Purpose](#project-purpose)
3. [Features](#features)
4. [Entities](#entities)
5. [Installation Instructions](#installation-instructions)
6. [Usage Guide](#usage-guide)
7. [License](#license)
8. [Feedback](#feedback)
9. [Contribution Guidelines](#contribution-guidelines)
10. [Acknowledgements](#acknowledgements)

---

## Team Members

- **Nikita Banetski** - [Nikita-B05](https://github.com/Nikita-B05)
- **Hrithik Shah** - [hrithik-shah](https://github.com/hrithik-shah)
- **Abhi Prajapati** - [abhi-praj](https://github.com/abhi-praj)
- **Marina Tanaka** - [marinatedshrimp](https://github.com/marinatedshrimp)

---

## Project Purpose

The Financial Literacy Game is an interactive and educational experience for players to learn about
personal finance and understand its impact on their future. Players guide a character through life stages, making
financial decisions that influence metrics such as net worth and happiness. By simulating real-world
financial challenges, the game aims to empower users with practical financial knowledge and decision-making skills.

---

## Features

**1. User Authentication**:
  - Secure sign-up and login system with password validation and appropriate username input verification.
  - Unique usernames for each account.

**2. Character Customization**:
  - Select from six avatar options.
  - If an avatar is not selected, do not let the user proceed.
  - Create a personalized and appropriate character name, verified using the NameAPI. Also accounts for empty name
input edge case.
  - Updates home page character image and name.
  - 

**3. Interactive Financial Decision-Making**:
  - Encounter realistic scenarios requiring financial decisions.
  - Accounts for making mandatory decisions.
  - Saves user's progress and lets them go back to the home page.
  - Decisions impact net worth and happiness.

**4. Settings Management**:
  - Change password feature.
  - Dark/light mode toggle.
  - Allows the user to log out.

**5. Decision Logs**:
  - View logs of past decisions and their effects on metrics.

**6. Winning and Losing Conditions**:
  - Win by navigating through all decisions without going bankrupt or having happiness hit 0.
  - Lose if net worth or happiness drops below zero.
  - Game resets after a win or loss.

---

## Entities

### User:
These are the entities that are stored for the user.
- `name`: String
- `password`: String
- `characterName`: String
- `age`: int
- `avatar`: Avatar
- `happiness`: int
- `salary`: int
- `assets`: Assets
- `liabilities`: Liabilities
- `decisions`: List<HashMap<String, Decision>>


### Avatar:
These are the entities that are stored for the user's avatar/character.
- `id`: String
- `imagePath`: String

### Assets:
These are the entities that are stored for the user's character assets.
- `home`: double
- `stocks`: ArrayList<Stock>
- `cash`: double
- `car`: double

### Liabilities:
These are the entities that are stored for the user's character liabilities.
- `loan`: double
- `creditCard`: double

### Stock:
These are the entities that are stored for the user's character stocks.
- `stockCode`: String
- `quantity`: int
- `buyPrice`: double
- `multiplier`: double

### Decision:
These are the entities that are stored for the user's decisions.
- `timestamp`: LocalDateTime
- `decisionText`: String
- `decisionResponse`: String
- `netWorthChange`: double
- `happinessChange`: double

---

## Installation Instructions

To install and run the Financial Literacy Game, ensure that you are on Mac or Windows and follow these steps:

**1. Install Maven:**  
    Ensure that you have Maven installed on your system. You can download Maven from
    [here](https://maven.apache.org/download.cgi), or you can run the following command in your terminal:

    mvn clean install


**2. Clone the repository from GitHub on any operating system:**
   ```bash
   git clone [repository-link]
   cd [repository-folder]
   ```

**3. Check Configuration:**   
To ensure that the game runs, you need to check that your settings are configured correctly.
- Check that you have Java installed on your system.
- Ensure that you are using Java version 11 or higher.
- Use SDK: "corretto-11" (Amazon Corretto 11.0.25 - aarch64).This can be selected by going to "File" ->
"Project Structure" -> "Project" and then seek for the "SDK" dropdown and 
select it. If you don't have it installed, you can select the download SDK option in SDK selector and get it from there.

**4. Retrieve and add all API Keys:**.  
To make the program run, you need to add all the API keys. To do this, create a config.properties file in the
repository folder and add the following API keys from these websites...  
  
     
- <u>Polygon API</u>:  
     To add the Polygon API key (Stock API), navigate to https://polygon.io/ and simply select 'Create API Key' on their
     home page and sign up/log in, and retrieve your API key there and add it to your config.properties file.  
  ```bash
  polygonApi.key=[YOUR_API_KEY]
  ```
- <u>Name API</u>:  
    To add the Name API key, navigate to https://nameapi.org/ and simply select 'Get API Key' in the top right corner
    of the website and input your email to make an account and receive your custom API key.
  ```bash
  nameapi.key=[YOUR_API_KEY]
  ```
- <u>MongoDB API</u>:  
    To add the MongoDB API key, navigate to https://www.mongodb.com/ and simply select 'Try Atlas for free' on their
    home page and sign up/log in. Now, in order to make a connection to the MongoDB database, you need to create a
    cluster and retrieve the connection string for it. If you do not have any clusters created, navigate to either
the "Create" button to create a new cluster or check your "All Clusters" section in case you have an unused free
cluster.
![Hosted Image](https://imgur.com/D6HSHuF.png)

  Once you create a cluster, or if you already have one, click the "Connect" button.

  ![Hosted Image](https://imgur.com/swRCvYF.png)

  Next, click the "Drivers" selection under "Connect to your application."

  ![Hosted Image](https://imgur.com/KtODsFz.png)

  Next, ensure that you driver is Java 5.1 or later and then copy your connection string in the blue box, as shown
  in the image below. Make sure to change your dbpassword to the password for your database username.

  ![Hosted Image](https://imgur.com/ExBFwdx.png)

  Once all is done. Take this API key and place it in your config.properties file and add the two lines below it as
well.

  ```bash
  mongo.uri=[YOUR_API_KEY]
  mongo.database=mydatabase  
  mongo.collection=users
  ```


**5. Run the game:**  
    Run the game by opening the Main.java file in the following directory: 

```bash
src/main/java/app/Main.java
```

---

## Usage Guide

Give a clear, accurate guide on how to use the software, once downloaded
• Use code examples (EX: how to start up or use the code), screenshots, .gif files, or
videos to make the README file clear and easy-to-use
Reference(s): Sullivan


---

## License

This project is licensed under the [MIT License](LICENSE).

In short, this means that you are free to use the code in any way you'd like, subject to the fact that the Software
is provided "as is", without warranty of any kind, express or implied (see MIT License for more details).

---

## Feedback

If you would like to provide feedback, please complete and submit our
[Google form](https://forms.gle/LctBk4iJfsMdNKft6).

---

## Contribution Guidelines

We welcome contributions to the Financial Literacy Game! Whether you are fixing bugs, improving documentation, or proposing new features, we encourage collaboration to make this project better. Please follow these guidelines when contributing:

### How to Contribute

**1. Report Issues**:
- If you encounter a bug or have a feature request, open an issue in the
   [GitHub Issues](https://github.com/Nikita-B05/CSC207-Group230/issues) section.
   Please include a clear description, steps to reproduce (if applicable), and screenshots (if relevant).

**2. Fork and Clone the Repository**:
- Fork the repository to your GitHub account.
- Set the upstream remote to sync changes from the main repository.

**3. Create a Branch**:  
- Use descriptive names for your branches:

**4. Write Clean and Clear Code**:  
- When coding, please follow SOLID design principles and clean architecture.
- Ensure your code is well-documented and easy to understand.

**6. Test Your Changes**:
- Ensure your changes do not break existing functionality.
- Add appropriate unit tests to validate your contributions.
- Run existing tests to ensure nothing else is broken.

### Guidelines for Pull Requests

**1. Create a Pull Request**:
- Push changes to your forked repository and open a pull request to the main branch.
- Provide a clear description of changes and their purpose.
- Ensure your pull request:
    - Passes all tests.
    - Is free of merge conflicts.
    - Is reviewed and approved by at least two maintainers.

**2. Commit Message Format**:
- Use descriptive commit messages such as: "Added a cancel button to the avatar selector page."

**3. Review and Feedback**:
- Reviewers may request changes or tests. Address these promptly.

**4. Merge Protocols**:
- Approved PRs will be merged by a maintainer. Contributors should not self-merge.

---

## Acknowledgements

Thank you for looking through our project! We hope you find it useful.

---

[open main file.mp4](..%2F..%2FVideos%2Fopen%20main%20file.mp4)