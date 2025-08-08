package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

@TeleOp
public class MagneticDemo extends LinearOpMode {
    boolean hasBeenPressed = false;

    @Override
    public void runOpMode() {
        TouchSensor sensor = hardwareMap.get(TouchSensor.class, "magnetic");

        DcMotor motor = hardwareMap.get(DcMotor.class, "motor");
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            if(gamepad1.a) {
                hasBeenPressed = false;
            }
            if(sensor.isPressed()) {
                hasBeenPressed = true;
            }
            if(hasBeenPressed) {
                motor.setPower(0.0);
            } else {
                motor.setPower(0.1);
            }

            telemetry.addData("Is activated", sensor.isPressed());
            telemetry.update();
            sleep(100);
        }
    }
}
