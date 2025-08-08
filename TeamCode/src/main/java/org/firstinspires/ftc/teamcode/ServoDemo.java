package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PwmControl;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

@TeleOp
public class ServoDemo extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo servo = hardwareMap.get(Servo.class, "servo");
        //ServoImplEx servo = hardwareMap.get(ServoImplEx.class, "servo");
        //servo.setPwmRange(new PwmControl.PwmRange(500, 2500));

        waitForStart();

        while(opModeIsActive()) {
            servo.setPosition(0.0);

            sleep(7500);

            servo.setPosition(1.0);

            sleep(7500);
        }
    }
}
