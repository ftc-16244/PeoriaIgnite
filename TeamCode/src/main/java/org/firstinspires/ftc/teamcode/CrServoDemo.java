package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;

@TeleOp
public class CrServoDemo extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        CRServo servo = hardwareMap.get(CRServo.class, "servo");

        waitForStart();

        while(opModeIsActive()) {
            servo.setPower(-1.0);

            sleep(500);

            servo.setPower(0.0);

            sleep(500);

            servo.setPower(1.0);

            sleep(500);
        }
    }
}
