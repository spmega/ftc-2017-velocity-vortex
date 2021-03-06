package org.firstinspires.ftc.teamcode.Shashank.shooter.power;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Shashank.shooter.beans.ShooterMotor;
import org.firstinspires.ftc.teamcode.Shashank.shooter.util.Constants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class KalminFilter {

    private static final double STD_DEVIATION=0.2;
    private double prevValue= Constants.REQUESTED_ETPS;
    private double prevError = 1D;
    private double trustVal;
    private volatile double filteredRPM = 0.0;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private int unfilteredRpm = 0;

    public KalminFilter(final ShooterMotor shooterMotor, final OpMode opMode) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while(!executorService.isShutdown()){
                    if(opMode.gamepad2.a)
                        unfilteredRpm = shooterMotor.getRpm();
                        filteredRPM = applyFilter(shooterMotor.getRpm());
                }
            }
        });
    }

    public int getUnfilteredRpm() {
        return unfilteredRpm;
    }

    public double getFilteredRPM() {
        return filteredRPM;
    }

    public double getTrustVal() {
        return trustVal;
    }

    public double getPrevError() {
        return prevError;
    }

    public double applyFilter(double rpm){
        trustVal = prevError / (prevError + STD_DEVIATION);
        double filteredData = prevValue + trustVal * (rpm - prevValue);

        prevError = (1 - trustVal) * prevError;
        prevValue = filteredData;

        return filteredData;
    }

    public void reset()
    {
        prevValue= Constants.REQUESTED_ETPS;
        prevError = 1D;

    }

    public void shutdown(){
        executorService.shutdown();
    }
}
