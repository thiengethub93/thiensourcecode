import random


class Player:

    moves = ['scissors', 'paper', 'rock']
    # inititiation move

    def __init__(self):
        self.p1_move = self.moves
        self.p2_move = random.choice(self.moves)

    def learn(self, p1_move, p2_move):
        self.p1_move = p1_move
        self.p2_move = p2_move


class SelfPlayer(Player):
    def move(self):
        while True:
            choose = input('Rock, paper, scissors for Player 1 \n').lower()
            if choose in self.moves:
                return choose
            else:
                print('Invalid Choice!')
            break


class ReflectPlayer(Player):
    def move(self):
        # reflects the choice of the previous round
        return self.p2_move


class CyclePlayer(Player):
    def move(self):
        if self.p1_move == self.moves[0]:
            return self.moves[1]
        elif self.p2_move == self.moves[1]:
            return self.moves[2]
        else:
            return self.moves[0]


class RandomPlayer(Player):
    def move(self):
        return self.p2_move


class Game:
    def __init__(self, p1, p2):
        self.p1 = p1
        self.p2 = p2
        self.score_p1 = 0
        self.score_p2 = 0

    def rounds(self):
        while True:
            self.round_numbers = input(
                "How many rounds do you want want play?")
            if self.round_numbers.isdigit():
                return self.round_numbers
            elif self.round_numbers.lower() == 'exit':
                exit()

    def beat(self, one, two):
        return ((one == 'rock' and two == 'scissors') or
                (one == 'scissors' and two == 'paper') or
                (one == 'paper' and two == 'rock'))

    def play_round(self):
        move1 = self.p1.move()
        move2 = self.p2.move()
        print(
            f"\n Player 1 move is : {move1}"
            f"\n Player 2 move is:  {move2}"
        )

        if self.beat(move1, move2):
            self.score_p1 += 1
        elif move1 == move2:
            self.score_p1 = self.score_p1
            self.score_p2 = self.score_p2
        else:
            self.score_p2 += 1
        self.p1.learn(move1, move2)
        self.p2.learn(move2, move1)

    def score_game(self):
        if self.score_p1 < self.score_p2:
            print(
                f"\n Score of Player 1: {self.score_p1}"
                f"\n Score of Player 2: {self.score_p2}"
                f"\n PLAYER 2 WIN"
            )
        elif self.score_p1 == self.score_p2:
            print(
                f"\n Score of Player 1: {self.score_p1}"
                f"\n Score of Player 2: {self.score_p2}"
                f"\n BOTH PLAYER Is TIE"
            )
        else:
            print(
                f"\n Score of Player 1: {self.score_p1}"
                f"\n Score of Player 2: {self.score_p2}"
                f"\n PLAYER 1 WIN"
            )

    def determine_winner(self):
        if self.score_p1 < self.score_p2:
            print(
                f"\n Total Score of Player 1: {self.score_p1}"
                f"\n Total Score of Player 2: {self.score_p2}"
                f"\n FINAL RESULT: PLAYER 2 WIN"
            )
        elif self.score_p1 == self.score_p2:
            print(
                f"\n Total Score of Player 1: {self.score_p1}"
                f"\n Total Score of Player 2: {self.score_p2}"
                f"\n FINAL RESULT: TIE"
            )
        else:
            print(
                f"\n Total Score of Player 1: {self.score_p1}"
                f"\n Total Score of Player 2: {self.score_p2}"
                f"\n FINAL RESULT: PLAYER 1 WIN"
            )

    def play_game(self):
        print("Game start!")
        self.rounds()
        for round in range(int(self.round_numbers)):
            self.play_round()
            self.score_game()
        self.determine_winner()


if __name__ == '__main__':
    RandomPlayer = Game(SelfPlayer(),
                        random.choice([RandomPlayer(), ReflectPlayer(), CyclePlayer()]))
    RandomPlayer.play_game()
