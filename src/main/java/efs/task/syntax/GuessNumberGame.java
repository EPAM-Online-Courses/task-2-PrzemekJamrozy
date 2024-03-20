package efs.task.syntax;

import java.util.*;

public class GuessNumberGame {

    private final int M;
    private final double L;
    private final int number;

    private List<String> trials;

    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {

        try{
            this.M = Integer.parseInt(argument);
        }catch(NumberFormatException e){
            System.out.println("'"+argument+"'"+ " to "+ UsefulConstants.WRONG_ARGUMENT + " - to nie liczba");
            throw new IllegalArgumentException();
        }

        if(M < 1 || M > 400) {
            System.out.println(argument + " to " + UsefulConstants.WRONG_ARGUMENT + " - jest spoza zakresu <1,"+UsefulConstants.MAX_UPPER_BOUND+">");
            throw new IllegalArgumentException();
        }

        Random random = new Random();
        this.number = random.nextInt(this.M)+1;
        this.L = Math.floor(Math.log((double)this.M)/Math.log(2)) + 1;

        trials = new ArrayList<>();
        for(int i = 0; i < L; i++) {
            trials.add(".");
        }

    }

    public void play() {
        int trial = 0;
        String guess;
        int answer;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1,"+ M +">");
        while (true){

            if(trial >= (int)this.L){
                System.out.println(UsefulConstants.UNFORTUNATELY+ ", wyczerpałeś limit prób ("+(int)this.L+") do odgadnięcia liczby "+this.number);
                break;
            }

            trials.set(trial,"*");

            System.out.print("Twoje próby: [");
            for(int i=0;i< trials.size();i++){
                System.out.print(trials.get(i));
            }
            System.out.println("]");

            System.out.println(UsefulConstants.GIVE_ME+" liczbę : ");

            guess = scanner.next();
            try{
                answer = Integer.parseInt(guess);
            }catch (Exception e){
                System.out.println(UsefulConstants.NOT_A_NUMBER);
                trial++;
                continue;
            }

            if(answer > number){
                System.out.println("To "+UsefulConstants.TO_MUCH);
            }else if(answer < number){
                System.out.println("To "+UsefulConstants.TO_LESS);
            }else{
                System.out.println("To "+UsefulConstants.YES);
                System.out.println(UsefulConstants.CONGRATULATIONS+", "+(trial+1)+" - tyle prób zajeło Ci odgadnięcie liczby "+this.number);
                break;
            }

            trial++;
        }

    }
}