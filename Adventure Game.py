import time
import random


def print_message(print_the_message):
    print(print_the_message)
    time.sleep(2)


def game_opening():
    print_message("Welcome to Adventure game application.\n")
    print_message("You're standing on the field.\n")
    print_message("The field with grasses and flowers.\n")
    print_message("There is a rumour about a monster around.\n")
    print_message("You will kill the monster to save the village.\n")
    print_message("The monster can be in the house on the left.\n")

    print_message("There will be a cave on the right\n")
    print_message("You will enter the cave to get the weapon.\n")


game_opening()


def cave(item, choice):
    if "sword" in item:
        print_message("You are entering the cave to look for the Sword.\n")
        print_message("This is a dark cave to look for a bright Sword.\n")
        print_message("You take the Sword with you and back the field.\n")
        house(item, choice)
    else:
        print_message("There will be no Sword in this cave.\n")
        print_message("It might be taken by someone else.\n")
        print_message("You have two options now.\n")
        while True:
            option = input("Would you like to fight or run away?")

            if option == "fight":
                print_message("Back to the field and turn left.\n")
                print_message("\nYou picked " + item + " to fight.\n")
                house(item, choice)
                break
            elif option == "run":
                field(item, option)
                break


def field(item, choice):
    while True:
        print_message("Knock the door to enter the house press K \n")
        print_message("Enter the cave press R \n")

        decision = input("(Please enter K or R.)\n")
        if decision == "R":
            cave(item, choice)
            break
        elif decision == "K":
            print_message("You wil fight by hands \n")
            print_message("So sorry you're defeat \n")
        play_again()
        break


def play_again():
    play = input("Would you like to restart or quit the game?").lower()
    if play == "restart":
        print_message("\n Please wait the game is restaring \n")
        play_game()
    elif play == "quit":
        print_message("\n Thanks for playing the game! See you again. \n")
    else:
        print_message("\n Sorry I don't understand.\n")


def house(item, choice):
    print_message("You're entering the house \n")
    print_message("This is the house of" + choice + ".")
    print_message("The" + choice + "is attacking you")
    if "sword" in item:
        print_message("You use the sword to stab at the" + choice + "\n")
        print_message("Congratulations! You're the winner:\n")
    else:
        print_message("You did try your best.\n")
        print_message("but your weapon is not good enough to fight:\n")
        print_message("You'lost the game!\n")
        print_message("The game is over!\n")
    play_again()


def play_game():
    item = random.choice(["sword", "gun", "knife"])
    choice = random.choice(["monster", "wicked witch", "evil"])
    field(item, choice)


play_game()
