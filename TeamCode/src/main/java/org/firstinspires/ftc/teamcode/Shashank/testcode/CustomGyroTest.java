package org.firstinspires.ftc.teamcode.Shashank.testcode;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

/**
 * Created by spmeg on 10/22/2016.
 */
@Autonomous(name = "CustomGyroTest", group = "Tests")
@Disabled
public class CustomGyroTest extends OpMode {
    ModernRoboticsI2cGyro gyro;   // Hardware Device Object
    int xVal, yVal, zVal = 0;     // Gyro rate Values
    int heading = 0;              // Gyro integrated heading
    boolean lastResetState = false;
    boolean curResetState  = false;
    int angleZ = 0;

    @Override
    public void init() {
        gyro = (ModernRoboticsI2cGyro)hardwareMap.gyroSensor.get("gyro");
        gyro.calibrate();
    }

    @Override
    public void loop() {
        while (gyro.isCalibrating())  {
            sleep(50);
        }

        xVal = gyro.rawX();
        yVal = gyro.rawY();
        zVal = gyro.rawZ();

        heading = gyro.getHeading();
        angleZ  = gyro.getIntegratedZValue();

        telemetry.addData("0", "Heading %03d", heading);
        telemetry.addData("1", "Int. Ang. %03d", angleZ);
        telemetry.addData("2", "X av. %03d", xVal);
        telemetry.addData("3", "Y av. %03d", yVal);
        telemetry.addData("4", "Z av. %03d", zVal);
        telemetry.addData(">", "Press A & B to reset Heading.");
        telemetry.addData("1", "Int. Ang. %03d", angleZ);
        telemetry.update();
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
