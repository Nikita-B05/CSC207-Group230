<h1 align="center" style="margin-bottom: 0em;">Financial Literacy Game</h1>
<h3 align="center" style="margin-top: 0; margin-bottom: 1.5em">CSC207-Group230</h3>

Our Financial Literacy Game is an interactive, educational experience designed to help students make informed financial
decisions. Players guide a character through life stages, facing real-world financial choices that impact their net
worth, happiness, and quality of life. By simulating financial decision-making, the game offers a fun, practical way to
build financial literacy and understand the long-term effects of money management.

**Wireframe** - [Figma Design](https://www.figma.com/design/0SzucedTjg2vrtVMyMrMeA/CSC207-Group230---Finance-Game-Wireframe?node-id=0-1&t=UUBZOjHSskBEjrA8-1)

---

## Team Members
- Nikita Banetski - [Nikita-B05](https://github.com/Nikita-B05)
- Hrithik Shah - [hrithik-shah](https://github.com/hrithik-shah)
- Abhi Prajapati - [abhi-praj](https://github.com/abhi-praj)
- Marina Tanaka - [marinatedshrimp](https://github.com/marinatedshrimp)

---

## Entities

### User
  - name: String
  - character: Image
  - happiness: int
  - qualityOfLife: int
  - assets: Assets
  - liabilities: Liabilities
  - newWorth: int
  - decisions: ArrayList<HashMap<String, Object>>

### Assets
  - home: int
  - stocks: ArrayList<Stock>
  - cash: int
  - car: int

### Liabilities
  - loan: int
  - creditCard: int
  - other recuring expenses

### Stock
  - stockCode: String
  - quantity: int
  - buyPrice: double
  - sellPrice: double
  - netResult: double
  - multiplier: int

---

## User Stories, Cases, and Interactions

#### **User Story 1: Playing the Finance Game - Nikita**
*As a student, I want to play a finance game where I make financial decisions that shape my character’s net worth,
happiness, and quality of life so that I can learn how financial choices impact long-term outcomes.*

- **Use Case**:
    - Make Financial Decisions: Users make choices affecting game metrics (net worth, happiness, quality of life).

- **User Interactions**:
    - Select answers via multiple-choice or numerical input.
    - View feedback on how decisions affect metrics.
    - Progress through various stages of life with new decision-making scenarios.

---

#### **User Story 2: Logging In / Signing Up - Hrithik/Nikita**
*As a new user, I want to sign up with a unique username and password so that I can create a secure account for the
game. As a returning user, I want to log in with my username and password so that I can access my account.*

- **Use Cases**:
    - Sign Up: Create a unique username and password (minimum of 8 characters).
    - Login: Enter username and password to access the game.

- **User Interactions**:
    - Enter username and password to sign up or log in.

---

#### **User Story 3: Character Customization - Nikita**
*As a player, I want to customize my character by selecting an avatar and setting a character name so that I can
personalize my gaming experience.*

- **Use Case**:
    - Select Avatar and Character Name: Users choose their avatar and enter a character name.

- **User Interactions**:
    - Choose an avatar from six image options.
    - Enter a character name, verified by API for appropriateness.

---

#### **User Story 4: Viewing Logs of Previous Decisions - Marina**
*As a player, I want to view the logs of my past decisions to see how they impacted my character’s metrics so that I
can reflect on my choices and learn from them.*

- **Use Case**:
    - View Decision History: Users review logs of past decisions and see the resulting changes in metrics.

- **User Interactions**:
    - Access a log of previous decisions with related changes in net worth, happiness, and quality of life.

---

#### **User Story 5: Profile Settings Management - Abhi**
*As a user, I want to manage my profile settings, including changing my password and logging out, so that I can keep
my account secure and control my access.*

- **Use Cases**:
    - Change Password: Users update their password.
    - Log Out: Users sign out of their account.
    - Changing UI Mode: Users can select the UI of their program to be in dark mode or light

- **User Interactions**:
    - Use the “Change Password” button to go to a page for password updates.
    - Use the “Log Out” button to return to the login page.
    - Use the Dark Mode checkbox to switch dark mode on or off.


---

#### **User Story 6: Homepage - Hrithik**
*As a player, I want to access the homepage to see general information about the game so that I can learn about
its purpose and how to play.*

- **Use Case**:
    - View Homepage: Users access the homepage to learn about the game and its features.

- **User Interactions**:
    - Access the homepage to learn about the game and its features.
    - View general information about the game, including its purpose and how to play.

---