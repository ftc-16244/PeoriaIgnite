package org.firstinspires.ftc.teamcode.Lift;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class GamepadLift extends LinearOpMode {
    @Override
    public void runOpMode() {
        Lift lift = new Lift(hardwareMap);

        waitForStart();

        lift.slideMechanicalReset();

        while (opModeIsActive()) {
            if (gamepad1.dpad_down) {
                lift.setSlideLevel0();
            }
            if (gamepad1.dpad_right) {
                lift.setSlideLevel1();
            }
            if (gamepad1.dpad_up) {
                lift.setSlideLevel2();
            }
            if (gamepad1.dpad_left) {
                lift.setSlideLevel3();
            }
            sleep(100);
        }
    }
}
