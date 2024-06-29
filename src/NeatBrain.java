/*
import org.encog.Encog;
import org.encog.ml.CalculateScore;
import org.encog.ml.MLMethod;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.ea.train.basic.BasicEA;
import org.encog.ml.ea.train.basic.TrainEA;
import org.encog.ml.ea.train.*;
import org.encog.neural.neat.*;
import org.encog.neural.neat.NEATNetwork;
import org.encog.neural.neat.NEATPopulation;

import org.encog.neural.neat.training.NEATGenome;
import org.encog.neural.neat.training.opp.NEATMutateAddNode;
import org.encog.neural.neat.training.opp.NEATMutateWeights;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.training.TrainingSetScore;
import org.encog.util.simple.EncogUtility;

import java.util.Arrays;

public class NeatBrain implements BrainInterface{
    int inputSize;
    int outputSize;
    double[] outputData;
    NEATNetwork brain;
    NEATMutateAddNode mutateAddNode = new NEATMutateAddNode();
    public NeatBrain(int inputSize, int outputSize) {
        this.inputSize = inputSize;
        this.outputSize = outputSize;
        this.outputData = new double[outputSize];
        NEATPopulation population = new NEATPopulation(inputSize, outputSize, 1);
        //CalculateScore score = new TrainingSetScore(new MLData(outputData));

        //final EvolutionaryAlgorithm train = NEATUtil.constructNEATTrainer(population,);
        //NEATNetwork network = (NEATNetwork)train.getCODEC().decode(train.getBestGenome());




    }
    public NeatBrain(NEATNetwork brain) {
        this.brain=brain;
    }


    @Override
    public double[] compute(double[] inputData) {
        MLData input = new BasicMLData(inputData);
        return brain.compute(input).getData();
    }

    @Override
    public BrainInterface copy() {
        NEATNetwork copiedNetwork = new NEATNetwork(brain.getInputCount(),brain.getOutputCount(), Arrays.asList(brain.getLinks()), brain.getActivationFunctions());
        return new NeatBrain(copiedNetwork);
    }

    @Override
    public void mutatedWeights(double perturbationRate) {
        return;
        //mutateAddNode.chooseRandomNeuron(brain,true);

    }
}*/
