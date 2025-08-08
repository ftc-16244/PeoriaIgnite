package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevTouchSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class TouchDemo extends LinearOpMode {
    @Override
    public void runOpMode() {
        RevTouchSensor sensor = hardwareMap.get(RevTouchSensor.class, "touch");

        DcMotor motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            if(!sensor.isPressed()) {
                motor.setPower(0.1);
            } else {
                motor.setPower(0);
            }

            telemetry.addData("Is pressed", sensor.isPressed());
            telemetry.update();
            sleep(100);
        }
    }
}
