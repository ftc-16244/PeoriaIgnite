package org.firstinspires.ftc.teamcode.Lift;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorImplEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Lift {
    private static DcMotorImplEx slideMotor;
    private final ElapsedTime runtime = new ElapsedTime();

    // constants
    private static final double SLIDE_HEIGHT_CORRECTION_FACTOR  = 1.00;

    private static final double TICKS_PER_MOTOR_REV_SLIDE       = 145.1; // 1150 motor
    private static final double PULLEY_DIA                      = 40;    // milimeters

    private static final double SLIDE_DISTANCE_PER_REV          = PULLEY_DIA * Math.PI / (25.4 * SLIDE_HEIGHT_CORRECTION_FACTOR);

    private static final double TICKS_PER_SLIDE_IN              = TICKS_PER_MOTOR_REV_SLIDE / SLIDE_DISTANCE_PER_REV;

    // motor power
    private static final double SLIDESPEED      = 1.0;
    private static final double SLIDERESETSPEED = -0.25;


    // inches
    private static final double SLIDE_LEVEL_0   = 0.1;
    private static final double SLIDE_LEVEL_1   = 10;
    private static final double SLIDE_LEVEL_2   = 20;
    private static final double SLIDE_LEVEL_3   = 40;


    public static double targetHeight = SLIDE_LEVEL_0;
    public static boolean inLevelZero = true;

    public Lift(HardwareMap hwMap) {
        slideMotor = hwMap.get(DcMotorImplEx.class, "liftMotor");
        slideMotor.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setSlideLevel0() {
        if(!inLevelZero) {
            targetHeight = (SLIDE_LEVEL_0);
            liftToTargetHeight(targetHeight);

            inLevelZero = true;

            //runtime.reset();
            //while(runtime.seconds() < 0.75) {}

            //slideMotor.setMotorDisable();
        }
    }
    public void setSlideLevel1() {
        targetHeight = (SLIDE_LEVEL_1);
        liftToTargetHeight(targetHeight);

        inLevelZero = false;
    }
    public void setSlideLevel2() {
        targetHeight = (SLIDE_LEVEL_2);
        liftToTargetHeight(targetHeight);

        inLevelZero = false;
    }
    public void setSlideLevel3() {
        targetHeight = (SLIDE_LEVEL_3);
        liftToTargetHeight(targetHeight);

        inLevelZero = false;
    }

    public void slideMechanicalReset() {
        //slideMotor.setMotorEnable();

        slideMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER); // need to switch off encoder to run with a timer
        slideMotor.setPower(SLIDERESETSPEED);

        runtime.reset();

        // allow the lift to retract
        while (runtime.seconds() < 1.0) {
            //Time wasting loop so setSlideLevel0slide can retract. Loop ends when time expires
        }

        // set everything back the way is was before reset so encoders can be used
        slideMotor.setPower(0);

        slideMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        runtime.reset();
        while (runtime.seconds() < 0.25) {
            //Time wasting loop so spring can relax. Loop ends when time expires
        }
        slideMotor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        setSlideLevel0();
    }

    public void liftToTargetHeight(double height) {
        //slideMotor.setMotorEnable();

        slideMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        int newTargetHeight;

        // Determine new target lift height in ticks
        newTargetHeight = (int)(height * TICKS_PER_SLIDE_IN);

        // Set the target now that is has been calculated
        slideMotor.setTargetPosition(newTargetHeight);

        // set the speed
        slideMotor.setPower(SLIDESPEED);

        // start moving
        slideMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
