public interface BrainInterface {

    double[] compute(double[] inputData);
    BrainInterface copy();
    void mutatedWeights(double perturbationRate);
}
