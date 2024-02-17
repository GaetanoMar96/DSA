package designpattern;

public class CommandPattern {

    interface Command {
        void execute();
    }

    static class Light {

        void turnOn() {
            System.out.println("Turn On");
        }

        void turnOff() {
            System.out.println("Turn Off");
        }
    }

    static class Controller {
        Command command;

        public Controller(Command command) {
            this.command = command;
        }

        public void press() {
            this.command.execute();
        }
    }

    static class TurnOn implements Command {

        Light light;

        public TurnOn(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            this.light.turnOn();
        }
    }

    static class TurnOff implements Command {

        Light light;

        public TurnOff(Light light) {
            this.light = light;
        }

        @Override
        public void execute() {
            this.light.turnOff();
        }
    }

    public static void main(String[] args) {
        Light light = new Light();
        Command turnOn = new TurnOn(light);
        Command turnOff = new TurnOff(light);

        Controller controllerOn = new Controller(turnOn);
        Controller controllerOff = new Controller(turnOff);

        controllerOn.press();
        controllerOff.press();
    }
}
