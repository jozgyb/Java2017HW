package hu.ppke.itk.java2018.jozgy.hf01;

import java.util.Arrays;

public class CounterWeightScale {

	private int[] counterWeights;

	CounterWeightScale(int[] counterWeights) {
		this.counterWeights = counterWeights;
	}

	int[] sulyokraValt(int t) {
		int[] indexOfWeights = new int[t + 1];
		int[] minimumQuantityOfWeights = new int[t + 1];

		int weight = 0, min;

		indexOfWeights[0] = 0;
		minimumQuantityOfWeights[0] = 0;

		for (int j = 0; j <= t; j++) {
			min = Integer.MAX_VALUE;
			for (int i = 0; i < counterWeights.length; i++) {
				if (counterWeights[i] <= j) {
					if (minimumQuantityOfWeights[j - counterWeights[i]] + 1 < min) {
						min = minimumQuantityOfWeights[j - counterWeights[i]] + 1;
						weight = i;
					}
				}
			}
			minimumQuantityOfWeights[j] = min;
			indexOfWeights[j] = weight;
		}
		int[] resultQuantityOfWeights = new int[counterWeights.length];

		Arrays.fill(resultQuantityOfWeights, 0);

		int indexForIncrement = t;

		while (indexForIncrement > 0) {
			resultQuantityOfWeights[indexOfWeights[indexForIncrement]]++;

			indexForIncrement = indexForIncrement - counterWeights[indexOfWeights[indexForIncrement]];
		}

		return resultQuantityOfWeights;
	}

	void sulyokatListaz(int[] s) {
		int sumMinWeights = 0;

		for (int i = 0; i < s.length; i++) {
			if (s[i] != 0) {
				System.out.printf("%ddb %dg, ", s[i], counterWeights[i]);
				sumMinWeights += s[i];
			}
		}
		System.out.printf("értékű súlyra, összesen %d darabra van minimálisan szükségünk a méréshez. \n",
				sumMinWeights);
	}

	public static void main(String[] args) {
		System.out.println("TESZT1");
		int[] availableWeights1 = { 1, 5, 20, 25 };
		int weightOfItemToScale1 = 42;
		System.out.println("Rendelkezésre álló ellensúlyok: " + Arrays.toString(availableWeights1));
		System.out.println("Mérendő tárgy súlya: " + weightOfItemToScale1);
		System.out.println("Eredmény:");

		CounterWeightScale scale1 = new CounterWeightScale(availableWeights1);
		scale1.sulyokatListaz(scale1.sulyokraValt(weightOfItemToScale1));

		System.out.println("TESZT2");
		int[] availableWeights2 = { 5, 25, 20, 100, 50, 200, 1, 2 };
		int weightOfItemToScale2 = 233;
		System.out.println("Rendelkezésre álló ellensúlyok: " + Arrays.toString(availableWeights2));
		System.out.println("Mérendő tárgy súlya: " + weightOfItemToScale2);
		System.out.println("Eredmény:");

		CounterWeightScale scale2 = new CounterWeightScale(availableWeights2);
		scale2.sulyokatListaz(scale2.sulyokraValt(weightOfItemToScale2));
	}

}
