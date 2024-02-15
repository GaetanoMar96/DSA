package assessment;

public class HackathonWinner {
    //Question asked on couple of HackerRank assessment
    public static String hackathonWinner(String erica, String bob) {
        int ericaPoints = calculatePoints(erica);
        int bobPoints = calculatePoints(bob);

        if (ericaPoints > bobPoints) {
            return "Erica";
        } else if (ericaPoints < bobPoints) {
            return "Bob";
        } else {
            return "Tie";
        }
    }

    private static int calculatePoints(String participant) {
        int points = 0;
        for (int i = 0; i < participant.length(); i++) {
            char c = participant.charAt(i);
            switch (c) {
                case 'E':
                    points += 1;
                    break;
                case 'M':
                    points += 3;
                    break;
                case 'H':
                    points += 5;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid problem difficulty: " + c);
            }
        }
        return points;
    }

    public static void main(String[] args) {
        String erica = "EHH";
        String bob = "EME";
        String winner = hackathonWinner(erica, bob);
        System.out.println("The winner is: " + winner);
    }
}
