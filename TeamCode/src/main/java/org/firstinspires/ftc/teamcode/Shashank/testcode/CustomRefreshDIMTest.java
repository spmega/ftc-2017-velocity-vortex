package org.firstinspires.ftc.teamcode.Shashank.testcode;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.usb.RobotArmingStateNotifier;
import com.qualcomm.robotcore.util.RobotLog;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Created by spmeg on 10/29/2016.
 */
@Autonomous(name = "CustomRefreshDIMTest", group = "Tests")
@Disabled
public class CustomRefreshDIMTest extends LinearOpMode {
    private DeviceInterfaceModule module;
    private ColorSensor colorSensor;

    private int memAddress = 0;
    private int length = 0;
    private int port = 0;
    private I2cAddr i2cAddr;
    @Override
    public void runOpMode() throws InterruptedException {
        colorSensor = hardwareMap.colorSensor.get("rcs");
        module = hardwareMap.deviceInterfaceModule.get("dim");

        waitForStart();

        RobotLog.d("Am now closing the module");
        telemetry.addData(">", "Am now reseting the module");
        module.close();
        module.resetDeviceConfigurationForOpMode();

        while ((opModeIsActive())){
            telemetry.addData(">", "Am now attempting to read data");
            telemetry.addData("color argb", colorSensor.argb());
            telemetry.update();
        }
    }
}
