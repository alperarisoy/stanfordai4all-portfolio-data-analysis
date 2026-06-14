public class PortfolioAnalysis {

    // Small Java practice version of the portfolio analysis idea
    // Burada sample price data kullandım.
    // Amaç: return, volatility ve basit portfolio mantığını Java ile denemek.

    static double[] tech = {100.0, 101.2, 100.7, 102.4, 103.1, 102.6, 104.0, 105.2};
    static double[] bank = {72.0, 72.5, 72.1, 73.0, 73.4, 73.2, 74.1, 74.6};
    static double[] energy = {56.0, 55.6, 56.4, 57.2, 56.8, 57.5, 58.1, 57.9};
    static double[] bond = {50.0, 50.1, 50.0, 50.2, 50.3, 50.2, 50.4, 50.5};

    public static double[] calculateReturns(double[] prices) {
        double[] returns = new double[prices.length - 1];

        for (int i = 1; i < prices.length; i++) {
            returns[i - 1] = (prices[i] - prices[i - 1]) / prices[i - 1];
        }

        return returns;
    }

    public static double average(double[] numbers) {
        double total = 0;

        for (double number : numbers) {
            total += number;
        }

        return total / numbers.length;
    }

    public static double volatility(double[] numbers) {
        double avg = average(numbers);
        double total = 0;

        for (double number : numbers) {
            total += Math.pow(number - avg, 2);
        }

        return Math.sqrt(total / numbers.length);
    }

    public static void printAssetSummary(String name, double[] returns) {
        System.out.println(name);
        System.out.println("  average daily return: " + Math.round(average(returns) * 100000.0) / 1000.0 + "%");
        System.out.println("  daily volatility: " + Math.round(volatility(returns) * 100000.0) / 1000.0 + "%");
        System.out.println();
    }

    public static void main(String[] args) {
        double[] techReturns = calculateReturns(tech);
        double[] bankReturns = calculateReturns(bank);
        double[] energyReturns = calculateReturns(energy);
        double[] bondReturns = calculateReturns(bond);

        System.out.println("Asset Summary");
        System.out.println("-------------");

        printAssetSummary("tech", techReturns);
        printAssetSummary("bank", bankReturns);
        printAssetSummary("energy", energyReturns);
        printAssetSummary("bond", bondReturns);

        double techWeight = 0.35;
        double bankWeight = 0.25;
        double energyWeight = 0.20;
        double bondWeight = 0.20;

        double[] portfolioReturns = new double[techReturns.length];

        for (int i = 0; i < portfolioReturns.length; i++) {
            portfolioReturns[i] =
                    techReturns[i] * techWeight +
                    bankReturns[i] * bankWeight +
                    energyReturns[i] * energyWeight +
                    bondReturns[i] * bondWeight;
        }

        double totalReturn = 1;

        for (double dailyReturn : portfolioReturns) {
            totalReturn = totalReturn * (1 + dailyReturn);
        }

        totalReturn = totalReturn - 1;

        System.out.println("Portfolio Result");
        System.out.println("----------------");
        System.out.println("total return: " + Math.round(totalReturn * 10000.0) / 100.0 + "%");
        System.out.println("average daily return: " + Math.round(average(portfolioReturns) * 100000.0) / 1000.0 + "%");
        System.out.println("daily volatility: " + Math.round(volatility(portfolioReturns) * 100000.0) / 1000.0 + "%");
    }
}
