import org.encog.engine.network.activation.*;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;

import java.util.Random;

public class DJLBrain2 implements BrainInterface{
    int inputSize;
    int outputSize;
    int[][] networkStructur;
    BasicNetwork network;
    ActivationFunction activationFunction;
    public DJLBrain2(int outputSize, int inputSize, int[][] networkStructur) {
        this.outputSize=outputSize;
        this.inputSize=inputSize;
        this.networkStructur=networkStructur;
        this.network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, inputSize));// Input layer with 2 neurons

        for (int i = 0; i < networkStructur.length; i++) {
            int nodes = networkStructur[i][0];
            int activationFunctionType = networkStructur[i][1];

            switchActivationFunction(activationFunctionType);
            network.addLayer(new BasicLayer(activationFunction, true, nodes));
        }
        
 // Hidden layer with 3 neurons
        switchActivationFunction(networkStructur[networkStructur.length-1][1]);
        network.addLayer(new BasicLayer(activationFunction, false, outputSize)); // Output layer with 1 neuron
        network.getStructure().finalizeStructure();
        network.reset();


    }

    @Override
    public double[] compute(double[] inputData) {
        double[] outputData = new double[outputSize];
        network.compute(inputData, outputData);
        return outputData;
    }
    @Override
    public void mutatedWeights(double perturbationRate) {
        Random rand = new Random();
        for (int layerIndex = 0; layerIndex < network.getLayerCount() - 1; layerIndex++) {
            int fromNeuronCount = network.getLayerTotalNeuronCount(layerIndex);
            int toNeuronCount = network.getLayerNeuronCount(layerIndex + 1);
            for (int fromNeuron = 0; fromNeuron < fromNeuronCount; fromNeuron++) {
                for (int toNeuron = 0; toNeuron < toNeuronCount; toNeuron++) {
                    double weight = network.getWeight(layerIndex, fromNeuron, toNeuron);
                    weight += (rand.nextDouble() - 0.5) * 2 * perturbationRate;
                    network.setWeight(layerIndex, fromNeuron, toNeuron, weight);
                }
            }
        }
    }
    @Override
    public DJLBrain2 copy() {
        DJLBrain2 copy = new DJLBrain2(this.outputSize,inputSize,networkStructur);
        for (int layerIndex = 0; layerIndex < network.getLayerCount() - 1; layerIndex++) {
            int fromNeuronCount = network.getLayerTotalNeuronCount(layerIndex);
            int toNeuronCount = network.getLayerNeuronCount(layerIndex + 1);
            for (int fromNeuron = 0; fromNeuron < fromNeuronCount; fromNeuron++) {
                for (int toNeuron = 0; toNeuron < toNeuronCount; toNeuron++) {
                    double weight = network.getWeight(layerIndex, fromNeuron, toNeuron);
                    copy.network.setWeight(layerIndex, fromNeuron, toNeuron, weight);
                }
            }
        }
        return copy;
    }

    public void switchActivationFunction(int activationFunctionType){
        switch (activationFunctionType) {
            case 1:
                activationFunction = new ActivationReLU();
                break;
            case 2:
                activationFunction = new ActivationSigmoid();
                break;
            case 3:
                activationFunction = new ActivationTANH();
                break;
            case 4:
                activationFunction = new ActivationLinear();
                break;
            case 5:
                activationFunction = new ActivationSoftMax();
                break;
            case 6:
                activationFunction = new ActivationStep();
                break;
            case 7:
                activationFunction = new ActivationRamp();
                break;
            case 8:
                activationFunction = new ActivationGaussian();
                break;
            default:
                activationFunction = new ActivationReLU();
        }

    }


}
