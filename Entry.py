from Elephant import Elephant
from Chessboard import Chessboard

def main():
    Chessboard1 = Chessboard()
    print("Start!")
    


    Elephant1 = Elephant(2, 1)
    print(Elephant1.getPositionX())
    Elephant1.move(1, 1)
    print(Elephant1)
    Chessboard1.update(Elephant1)



if __name__ == "__main__":
    main()