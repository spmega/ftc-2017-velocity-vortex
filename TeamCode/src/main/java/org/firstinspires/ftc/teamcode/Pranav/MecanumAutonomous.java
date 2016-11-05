/* Copyright (c) 2015 Qualcomm Technologies Inc
All rights reserved.
Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:
Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.
Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.
Neither the name of Qualcomm Technologies Inc nor the names of its contributors
may be used to endorse or promote products derived from this software without
specific prior written permission.
NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. */

package org.firstinspires.ftc.teamcode.Pranav;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Mecanum Autonomous", group = "Sensor")
//@Disabled

/*
This is an example of a Basic Autonomous utilizing the functions from RobotHardware Class
 */
public class MecanumAutonomous extends LinearOpMode
{
  @Override
  public void runOpMode() throws InterruptedException
  {

    //Use this command to access the all the functions in the RobotHardware Class
    MecanumHardware mecanum = new MecanumHardware();
    //Use this command to initialize the robot in the RobotHardware Class
    mecanum.init(hardwareMap);

      // wait for the start button to be pressed.
      waitForStart();
      {
        while (mecanum.gyro.isCalibrating())
        {
          Thread.sleep(50);
        }

          //An example of using the Drive function from the RobotHardware Class
          mecanum.drive(mecanum.ROTATION * 2, 0.5);

          //A little bit of settling time
          sleep(500);

          mecanum.turnGyro("right",45, 0.5);

          sleep(500);
      }


    while(true)
    {
      //Getting the Lego Line Sensor Values
      //telemetry.addData("Light: %d", robot.LegoLineSensor.getLightDetected());*
      telemetry.update();
    }




  }
}