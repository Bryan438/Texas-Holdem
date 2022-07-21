import java.lang.*;
import Table.Playing_Table;

public class Main {

        public static void main(String[] args) {
            Playing_Table table = new Playing_Table();
            table.preFlop();
            table.checkTotalPot();
            table.Flop();
            table.Turn();
            table.River();
            table.checkTotalPot();

        }

}

