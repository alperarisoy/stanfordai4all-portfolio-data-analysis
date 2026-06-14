# Portfolio Analysis Mini Project
# This is a small learning project about price data, returns and volatility.
# I used sample data here so the project can run without any external file.

prices = {
    "tech":   [100.0, 101.2, 100.7, 102.4, 103.1, 102.6, 104.0, 105.2],
    "bank":   [72.0, 72.5, 72.1, 73.0, 73.4, 73.2, 74.1, 74.6],
    "energy": [56.0, 55.6, 56.4, 57.2, 56.8, 57.5, 58.1, 57.9],
    "bond":   [50.0, 50.1, 50.0, 50.2, 50.3, 50.2, 50.4, 50.5],
}


def calculate_returns(price_list):
    # bir önceki güne göre günlük getiri hesaplıyorum
    returns = []

    for i in range(1, len(price_list)):
        daily_return = (price_list[i] - price_list[i - 1]) / price_list[i - 1]
        returns.append(daily_return)

    return returns


def average(numbers):
    return sum(numbers) / len(numbers)


def volatility(numbers):
    # basit standart sapma hesabı
    avg = average(numbers)
    squared_differences = []

    for number in numbers:
        squared_differences.append((number - avg) ** 2)

    variance = sum(squared_differences) / len(squared_differences)
    return variance ** 0.5


def print_asset_summary():
    print("Asset Summary")
    print("-------------")

    all_returns = {}

    for asset in prices:
        returns = calculate_returns(prices[asset])
        all_returns[asset] = returns

        avg_return = average(returns)
        risk = volatility(returns)

        print(asset)
        print("  average daily return:", round(avg_return * 100, 3), "%")
        print("  daily volatility:", round(risk * 100, 3), "%")
        print()

    return all_returns


def calculate_portfolio(all_returns):
    # Basit örnek ağırlıklar
    weights = {
        "tech": 0.35,
        "bank": 0.25,
        "energy": 0.20,
        "bond": 0.20,
    }

    portfolio_returns = []

    number_of_days = len(all_returns["tech"])

    for day in range(number_of_days):
        daily_portfolio_return = 0

        for asset in weights:
            daily_portfolio_return += all_returns[asset][day] * weights[asset]

        portfolio_returns.append(daily_portfolio_return)

    total_return = 1

    for r in portfolio_returns:
        total_return = total_return * (1 + r)

    total_return = total_return - 1

    print("Portfolio Weights")
    print("-----------------")
    print(weights)
    print()

    print("Portfolio Result")
    print("----------------")
    print("total return:", round(total_return * 100, 2), "%")
    print("average daily return:", round(average(portfolio_returns) * 100, 3), "%")
    print("daily volatility:", round(volatility(portfolio_returns) * 100, 3), "%")


all_returns = print_asset_summary()
calculate_portfolio(all_returns)
