package com.github.alexthe666.iceandfire.client.model;

import net.ilexiconn.llibrary.client.model.ModelAnimator;
import net.ilexiconn.llibrary.client.model.tools.AdvancedModelRenderer;
import net.ilexiconn.llibrary.server.animation.IAnimatedEntity;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFireDragon extends ModelDragonBase {
	public AdvancedModelRenderer BodyLower;
	public AdvancedModelRenderer Tail1;
	public AdvancedModelRenderer ThighR;
	public AdvancedModelRenderer ThighL;
	public AdvancedModelRenderer Spike8;
	public AdvancedModelRenderer Spike9;
	public AdvancedModelRenderer BodyUpper;
	public AdvancedModelRenderer Tail2;
	public AdvancedModelRenderer Spike10;
	public AdvancedModelRenderer Tail3;
	public AdvancedModelRenderer Spike11;
	public AdvancedModelRenderer Spike12;
	public AdvancedModelRenderer Tail4;
	public AdvancedModelRenderer Spike13;
	public AdvancedModelRenderer Spike14;
	public AdvancedModelRenderer Spike15;
	public AdvancedModelRenderer Club;
	public AdvancedModelRenderer Spike16;
	public AdvancedModelRenderer Spike17;
	public AdvancedModelRenderer Spike18;
	public AdvancedModelRenderer Spike19;
	public AdvancedModelRenderer Spike20;
	public AdvancedModelRenderer LegR;
	public AdvancedModelRenderer ToeR2;
	public AdvancedModelRenderer ToeR3;
	public AdvancedModelRenderer ToeR1;
	public AdvancedModelRenderer LegL;
	public AdvancedModelRenderer ToeL2;
	public AdvancedModelRenderer ToeL1;
	public AdvancedModelRenderer ToeL3;
	public AdvancedModelRenderer Neck1;
	public AdvancedModelRenderer ArmR1;
	public AdvancedModelRenderer ArmL1;
	public AdvancedModelRenderer Spike5;
	public AdvancedModelRenderer Spike6;
	public AdvancedModelRenderer Spike7;
	public AdvancedModelRenderer Neck2;
	public AdvancedModelRenderer Spike4;
	public AdvancedModelRenderer Neck3;
	public AdvancedModelRenderer Spike2;
	public AdvancedModelRenderer Spike3;
	public AdvancedModelRenderer Head;
	public AdvancedModelRenderer Spike1;
	public AdvancedModelRenderer HeadFront;
	public AdvancedModelRenderer Jaw;
	public AdvancedModelRenderer HornR;
	public AdvancedModelRenderer HornL;
	public AdvancedModelRenderer HornR3;
	public AdvancedModelRenderer HornL3;
	public AdvancedModelRenderer Teeth1;
	public AdvancedModelRenderer Teeth2;
	public AdvancedModelRenderer HornR2;
	public AdvancedModelRenderer HornL2;
	public AdvancedModelRenderer Rmembrane1;
	public AdvancedModelRenderer ArmR2;
	public AdvancedModelRenderer Rmembrane2;
	public AdvancedModelRenderer FingerR1;
	public AdvancedModelRenderer FingerR3;
	public AdvancedModelRenderer FingerR4;
	public AdvancedModelRenderer ClawR;
	public AdvancedModelRenderer ClawRPivot;
	public AdvancedModelRenderer FingerR2;
	public AdvancedModelRenderer Rmembrane4;
	public AdvancedModelRenderer Rmembrane3;
	public AdvancedModelRenderer ClawR2;
	public AdvancedModelRenderer ClawLPivot;
	public AdvancedModelRenderer ClawR3;
	public AdvancedModelRenderer ArmL2;
	public AdvancedModelRenderer Lmembrane1;
	public AdvancedModelRenderer FingerL1;
	public AdvancedModelRenderer FingerL4;
	public AdvancedModelRenderer FingerL3;
	public AdvancedModelRenderer Lmembrane2;
	public AdvancedModelRenderer ClawL;
	public AdvancedModelRenderer FingerL2;
	public AdvancedModelRenderer Lmembrane4;
	public AdvancedModelRenderer Lmembrane3;
	public AdvancedModelRenderer ClawL2;
	public AdvancedModelRenderer ClawL3;
	private ModelAnimator animator;

	public ModelFireDragon() {
		this.textureWidth = 256;
		this.textureHeight = 128;
		this.BodyUpper = new AdvancedModelRenderer(this, 67, 47);
		this.BodyUpper.setRotationPoint(0.0F, 0.609567366153918F, -6.672213098074744F);
		this.BodyUpper.addBox(-3.0F, -1.8F, 0.0F, 6, 7, 7, 0.0F);
		this.setRotateAngle(BodyUpper, 0.091106186954104F, 0.0F, 0.0F);
		this.Neck3 = new AdvancedModelRenderer(this, 25, 64);
		this.Neck3.setRotationPoint(0.0F, 0.0F, -3.9F);
		this.Neck3.addBox(-1.0F, -0.6F, -7.0F, 3, 4, 6, 0.0F);
		this.FingerR1 = new AdvancedModelRenderer(this, 128, 19);
		this.FingerR1.setRotationPoint(-11.7F, -0.1F, -0.3F);
		this.FingerR1.addBox(-16.0F, -0.5F, -1.0F, 16, 1, 2, 0.0F);
		this.setRotateAngle(FingerR1, -0.0F, 1.48352986419518F, 0.015707963267948967F);
		this.FingerR2 = new AdvancedModelRenderer(this, 128, 24);
		this.FingerR2.setRotationPoint(-15.2F, 0.1F, -0.2F);
		this.FingerR2.addBox(-10.0F, -0.5F, -1.0F, 10, 1, 2, 0.0F);
		this.setRotateAngle(FingerR2, -0.0F, 0.3590142271352336F, 0.0F);
		this.FingerL1 = new AdvancedModelRenderer(this, 128, 19);
		this.FingerL1.setRotationPoint(-11.7F, -0.1F, 0.3F);
		this.FingerL1.addBox(-16.0F, -0.5F, -1.0F, 16, 1, 2, 0.0F);
		this.setRotateAngle(FingerL1, 0.0F, -1.48352986419518F, 0.015707963267948967F);
		this.FingerL3 = new AdvancedModelRenderer(this, 128, 31);
		this.FingerL3.setRotationPoint(-8.3F, 0.0F, -0.2F);
		this.FingerL3.addBox(-17.0F, -0.5F, -1.0F, 16, 1, 2, 0.0F);
		this.setRotateAngle(FingerL3, 0.0F, -1.9198621771937625F, 0.0F);
		this.HeadFront = new AdvancedModelRenderer(this, 6, 44);
		this.HeadFront.setRotationPoint(0.5F, 0.0F, 0.0F);
		this.HeadFront.addBox(-1.5F, -2.8F, -8.8F, 3, 3, 5, 0.0F);
		this.setRotateAngle(HeadFront, -0.03237238967054832F, -0.0F, 0.0F);
		this.Tail2 = new AdvancedModelRenderer(this, 95, 33);
		this.Tail2.setRotationPoint(0.0F, -0.1F, 5.8F);
		this.Tail2.addBox(-1.5F, -1.6F, 0.9F, 3, 4, 8, 0.0F);
		this.setRotateAngle(Tail2, -0.045553093477052F, -0.0F, 0.0F);
		this.Club = new AdvancedModelRenderer(this, 42, 17);
		this.Club.setRotationPoint(0.0F, -0.4F, 7.5F);
		this.Club.addBox(-2.0F, -0.4F, 1.0F, 4, 2, 8, 0.0F);
		this.setRotateAngle(Club, -0.045553093477052F, -0.0F, 0.0F);
		this.Rmembrane2 = new AdvancedModelRenderer(this, 34, 74);
		this.Rmembrane2.setRotationPoint(-5.5F, 0.0F, 0.0F);
		this.Rmembrane2.addBox(-7.5F, -0.1F, 1.21F, 15, 0, 12, 0.0F);
		this.setRotateAngle(Rmembrane2, 0.0F, 0.6108652381980153F, 0.0F);
		this.Rmembrane1 = new AdvancedModelRenderer(this, 0, 78);
		this.Rmembrane1.setRotationPoint(-4.5F, 0.0F, 0.0F);
		this.Rmembrane1.addBox(-4.5F, 0.0F, 0.5F, 9, 0, 8, 0.0F);
		this.Neck2 = new AdvancedModelRenderer(this, 42, 44);
		this.Neck2.setRotationPoint(0.0F, -0.8F, -3.7F);
		this.Neck2.addBox(-1.0F, -0.8F, -5.0F, 3, 5, 4, 0.0F);
		this.setRotateAngle(Neck2, -0.06667157742618339F, -0.0F, 0.0F);
		this.ThighR = new AdvancedModelRenderer(this, 0, 23);
		this.ThighR.mirror = true;
		this.ThighR.setRotationPoint(-3.7F, 1.6F, 6.0F);
		this.ThighR.addBox(-1.43F, -0.5F, -2.0F, 3, 7, 4, 0.0F);
		this.setRotateAngle(ThighR, -0.19198621771937624F, -0.0F, 0.0F);
		this.Neck1 = new AdvancedModelRenderer(this, 57, 43);
		this.Neck1.setRotationPoint(-0.5F, 0.5F, 1.8F);
		this.Neck1.addBox(-1.5F, -2.0F, -5.0F, 4, 6, 4, 0.0F);
		this.setRotateAngle(Neck1, 0.091106186954104F, -0.0F, 0.0F);
		this.LegR = new AdvancedModelRenderer(this, 15, 23);
		this.LegR.setRotationPoint(0.0F, 4.3F, 1.9F);
		this.LegR.addBox(-1.0F, -0.5F, 0.0F, 2, 7, 2, 0.0F);
		this.setRotateAngle(LegR, -0.33161255787892263F, -0.0F, 0.0F);
		this.FingerL2 = new AdvancedModelRenderer(this, 128, 24);
		this.FingerL2.setRotationPoint(-15.2F, 0.1F, -0.2F);
		this.FingerL2.addBox(-10.0F, -0.5F, -0.7F, 10, 1, 2, 0.0F);
		this.setRotateAngle(FingerL2, -0.0F, -0.3590142271352336F, 0.0F);
		this.ArmL1 = new AdvancedModelRenderer(this, 132, 3);
		this.ArmL1.mirror = true;
		this.ArmL1.setRotationPoint(2.2F, 0.0F, 2.7F);
		this.ArmL1.addBox(-9.5F, -1.0F, -1.5F, 9, 2, 4, 0.0F);
		this.setRotateAngle(ArmL1, -0.4886921905584123F, 3.07177948351002F, 0.3490658503988659F);
		this.ClawL3 = new AdvancedModelRenderer(this, 128, 61);
		this.ClawL3.mirror = true;
		this.ClawL3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ClawL3.addBox(-0.8F, 0.7F, 0.0F, 2, 1, 1, 0.0F);
		this.Teeth1 = new AdvancedModelRenderer(this, 6, 16);
		this.Teeth1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Teeth1.addBox(-1.6F, 0.1F, -8.9F, 2, 1, 5, 0.0F);
		this.Spike20 = new AdvancedModelRenderer(this, 22, 34);
		this.Spike20.setRotationPoint(0.0F, 0.0F, 8.6F);
		this.Spike20.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(Spike20, 0.3731102663676185F, -0.0F, 0.0F);
		this.ToeL2 = new AdvancedModelRenderer(this, 1, 63);
		this.ToeL2.setRotationPoint(0.0F, 6.0F, -0.2F);
		this.ToeL2.addBox(-0.5F, -0.8F, -4.0F, 1, 2, 5, 0.0F);
		this.setRotateAngle(ToeL2, 0.6457718232379019F, 0.0F, 0.0F);
		this.ToeR2 = new AdvancedModelRenderer(this, 1, 63);
		this.ToeR2.setRotationPoint(0.0F, 6.0F, -0.2F);
		this.ToeR2.addBox(-0.5F, -0.8F, -4.0F, 1, 2, 5, 0.0F);
		this.setRotateAngle(ToeR2, 0.6457718232379019F, 0.0F, 0.0F);
		this.HornR3 = new AdvancedModelRenderer(this, 36, 28);
		this.HornR3.setRotationPoint(-1.0F, -0.8F, -0.8F);
		this.HornR3.addBox(-0.4F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(HornR3, -0.05235987755982988F, -0.3141592653589793F, 0.0F);
		this.HornL2 = new AdvancedModelRenderer(this, 46, 36);
		this.HornL2.mirror = true;
		this.HornL2.setRotationPoint(-0.4F, 0.3F, 3.6F);
		this.HornL2.addBox(-0.01F, -0.8F, -0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(HornL2, -0.07504915783575616F, 0.0F, 0.0F);
		this.Tail1 = new AdvancedModelRenderer(this, 75, 35);
		this.Tail1.setRotationPoint(0.0F, 0.8F, 5.5F);
		this.Tail1.addBox(-2.0F, -2.0F, 1.0F, 4, 5, 6, 0.0F);
		this.setRotateAngle(Tail1, -0.136659280431156F, -0.0F, 0.0F);
		this.Spike11 = new AdvancedModelRenderer(this, 34, 34);
		this.Spike11.setRotationPoint(0.0F, -1.2F, 2.3F);
		this.Spike11.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(Spike11, 0.8178612874845427F, -0.0F, 0.0F);
		this.Rmembrane3 = new AdvancedModelRenderer(this, 88, 74);
		this.Rmembrane3.setRotationPoint(-5.6F, 0.0F, 0.3F);
		this.Rmembrane3.addBox(-7.0F, -0.3F, -13.0F, 14, 0, 12, 0.0F);
		this.setRotateAngle(Rmembrane3, 3.141592653589793F, 3.141592653589793F, 3.141592653589793F);
		this.ToeR1 = new AdvancedModelRenderer(this, 1, 63);
		this.ToeR1.setRotationPoint(1.0F, 6.1F, 0.7F);
		this.ToeR1.addBox(-0.5F, -0.8F, -4.0F, 1, 2, 5, 0.0F);
		this.setRotateAngle(ToeR1, 0.5235987755982988F, 0.0F, 0.0F);
		this.FingerR4 = new AdvancedModelRenderer(this, 128, 38);
		this.FingerR4.setRotationPoint(-7.5F, 0.0F, 1.3F);
		this.FingerR4.addBox(-14.0F, -0.5F, -1.0F, 14, 1, 2, 0.0F);
		this.setRotateAngle(FingerR4, 3.141592653589793F, 0.45378560551852565F, 3.141592653589793F);
		this.ClawL = new AdvancedModelRenderer(this, 128, 44);
		this.ClawL.mirror = true;
		this.ClawL.addBox(-0.3F, -0.8F, 0.0F, 2, 2, 1, 0.0F);
		this.ClawLPivot = new AdvancedModelRenderer(this, 128, 44);
		this.ClawLPivot.setRotationPoint(-11.5F, 0.0F, 0.9F);
		this.setRotateAngle(ClawLPivot, 3.141592653589793F, -0.8726646259971648F, -2.6179938779914944F);
		this.LegL = new AdvancedModelRenderer(this, 15, 23);
		this.LegL.mirror = true;
		this.LegL.setRotationPoint(0.0F, 4.3F, 1.9F);
		this.LegL.addBox(-1.0F, -0.5F, 0.0F, 2, 7, 2, 0.0F);
		this.setRotateAngle(LegL, -0.33161255787892263F, -0.0F, 0.0F);
		this.Spike7 = new AdvancedModelRenderer(this, 22, 34);
		this.Spike7.mirror = true;
		this.Spike7.setRotationPoint(0.0F, -1.0F, 6.5F);
		this.Spike7.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(Spike7, 0.8178612874845427F, -0.0F, 0.0F);
		this.ClawR3 = new AdvancedModelRenderer(this, 128, 61);
		this.ClawR3.mirror = true;
		this.ClawR3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.ClawR3.addBox(-0.8F, 0.7F, -1.0F, 2, 1, 1, 0.0F);
		this.Spike2 = new AdvancedModelRenderer(this, 34, 34);
		this.Spike2.setRotationPoint(0.5F, -0.1F, -4.5F);
		this.Spike2.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(Spike2, 0.8178612874845427F, -0.0F, 0.0F);
		this.Spike3 = new AdvancedModelRenderer(this, 34, 34);
		this.Spike3.setRotationPoint(0.5F, -0.3F, -2.0F);
		this.Spike3.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(Spike3, 0.9032080002802318F, -0.0F, 0.0F);
		this.Spike12 = new AdvancedModelRenderer(this, 34, 34);
		this.Spike12.setRotationPoint(0.0F, -1.1F, 5.8F);
		this.Spike12.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(Spike12, 0.8178612874845427F, -0.0F, 0.0F);
		this.Tail4 = new AdvancedModelRenderer(this, 94, 20);
		this.Tail4.setRotationPoint(0.0F, -0.2F, 8.6F);
		this.Tail4.addBox(-1.0F, -0.9F, 1.0F, 2, 2, 8, 0.0F);
		this.Teeth2 = new AdvancedModelRenderer(this, 6, 16);
		this.Teeth2.mirror = true;
		this.Teeth2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Teeth2.addBox(-0.4F, 0.1F, -8.9F, 2, 1, 5, 0.0F);
		this.Head = new AdvancedModelRenderer(this, 6, 54);
		this.Head.setRotationPoint(0.0F, 2.2F, -6.2F);
		this.Head.addBox(-1.5F, -3.0F, -4.0F, 4, 4, 4, 0.0F);
		this.setRotateAngle(Head, 0.36425021489121656F, -0.0F, 0.0F);
		this.Lmembrane4 = new AdvancedModelRenderer(this, 139, 86);
		this.Lmembrane4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Lmembrane4.addBox(-25.0F, -0.1F, -16.0F, 26, 0, 15, 0.0F);
		this.HornR2 = new AdvancedModelRenderer(this, 46, 36);
		this.HornR2.setRotationPoint(-0.4F, 0.3F, 3.6F);
		this.HornR2.addBox(0.01F, -0.8F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(HornR2, -0.07504915783575616F, 0.0F, 0.0F);
		this.Rmembrane4 = new AdvancedModelRenderer(this, 139, 71);
		this.Rmembrane4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.Rmembrane4.addBox(-25.0F, -0.1F, 1.0F, 26, 0, 15, 0.0F);
		this.setRotateAngle(Rmembrane4, -0.0F, 0.003490658503988659F, 0.0F);
		this.Spike8 = new AdvancedModelRenderer(this, 22, 34);
		this.Spike8.setRotationPoint(0.0F, -0.8F, 2.8F);
		this.Spike8.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(Spike8, 0.8178612874845427F, -0.0F, 0.0F);
		this.Spike15 = new AdvancedModelRenderer(this, 34, 34);
		this.Spike15.setRotationPoint(0.0F, -0.6F, 8.9F);
		this.Spike15.addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.setRotateAngle(Spike15, 0.8178612874845427F, -0.0F, 0.0F);
		this.ToeL3 = new AdvancedModelRenderer(this, 1, 63);
		this.ToeL3.setRotationPoint(-1.0F, 6.1F, 0.7F);
		this.ToeL3.addBox(-0.5F, -0.8F, -4.0F, 1, 2, 5, 0.0F);
		this.setRotateAngle(ToeL3, 0.5235987755982988F, 0.0F, 0.0F);
		this.Spike1 = new AdvancedModelRenderer(this, 34, 34);
		this.Spike1.setRotationPoint(0.5F, 0.0F, -3.4F);
		this.Spike1.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(Spike1, 0.8178612874845427F, -0.0F, 0.0F);
		this.ToeL1 = new AdvancedModelRenderer(this, 1, 63);
		this.ToeL1.setRotationPoint(1.0F, 6.1F, 0.7F);
		this.ToeL1.addBox(-0.5F, -0.8F, -4.0F, 1, 2, 5, 0.0F);
		this.setRotateAngle(ToeL1, 0.5235987755982988F, 0.0F, 0.0F);
		this.Spike13 = new AdvancedModelRenderer(this, 34, 34);
		this.Spike13.setRotationPoint(0.0F, -0.9F, 2.0F);
		this.Spike13.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(Spike13, 0.8178612874845427F, -0.0F, 0.0F);
		this.FingerL4 = new AdvancedModelRenderer(this, 128, 38);
		this.FingerL4.setRotationPoint(-7.5F, 0.0F, -1.3F);
		this.FingerL4.addBox(-14.0F, -0.5F, -1.0F, 14, 1, 2, 0.0F);
		this.setRotateAngle(FingerL4, 3.141592653589793F, -0.45378560551852565F, 3.141592653589793F);
		this.Lmembrane3 = new AdvancedModelRenderer(this, 88, 74);
		this.Lmembrane3.mirror = true;
		this.Lmembrane3.setRotationPoint(-5.6F, -0.3F, -0.3F);
		this.Lmembrane3.addBox(-7.0F, 0.0F, -13.0F, 14, 0, 12, 0.0F);
		this.setRotateAngle(Lmembrane3, 0.0F, 3.141592653589793F, 0.0F);
		this.ClawR = new AdvancedModelRenderer(this, 128, 44);
		this.ClawR.mirror = true;
		this.ClawR.addBox(-0.3F, -0.8F, -1.0F, 2, 2, 1, 0.0F);
		this.ClawRPivot = new AdvancedModelRenderer(this, 128, 44);
		this.ClawRPivot.setRotationPoint(-11.5F, 0.0F, -0.9F);
		this.setRotateAngle(ClawRPivot, 3.141592653589793F, 0.8726646259971648F, -2.6179938779914944F);
		this.Spike14 = new AdvancedModelRenderer(this, 34, 34);
		this.Spike14.setRotationPoint(0.0F, -0.8F, 5.5F);
		this.Spike14.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(Spike14, 0.8178612874845427F, -0.0F, 0.0F);
		this.HornR = new AdvancedModelRenderer(this, 59, 34);
		this.HornR.setRotationPoint(-0.8F, -2.5F, -1.0F);
		this.HornR.addBox(-0.4F, -0.5F, 0.0F, 1, 2, 4, 0.0F);
		this.setRotateAngle(HornR, 0.3141592653589793F, -0.33161255787892263F, -0.19198621771937624F);
		this.Spike4 = new AdvancedModelRenderer(this, 34, 34);
		this.Spike4.setRotationPoint(0.5F, -1.6F, -3.2F);
		this.Spike4.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(Spike4, 0.8178612874845427F, -0.0F, 0.0F);
		this.ArmL2 = new AdvancedModelRenderer(this, 132, 11);
		this.ArmL2.mirror = true;
		this.ArmL2.setRotationPoint(-9.6F, 0.0F, 0.6F);
		this.ArmL2.addBox(-12.5F, -1.0F, -1.5F, 13, 2, 3, 0.0F);
		this.setRotateAngle(ArmL2, -0.08726646259971647F, 0.8552113334772213F, 0.0F);
		this.ArmR1 = new AdvancedModelRenderer(this, 132, 3);
		this.ArmR1.setRotationPoint(-2.2F, 0.0F, 2.7F);
		this.ArmR1.addBox(-9.5F, -1.0F, -2.5F, 9, 2, 4, 0.0F);
		this.setRotateAngle(ArmR1, 0.4886921905584123F, 0.06981317007977318F, -0.3490658503988659F);
		this.ClawR2 = new AdvancedModelRenderer(this, 128, 52);
		this.ClawR2.mirror = true;
		this.ClawR2.setRotationPoint(2.0F, 0.0F, 0.0F);
		this.ClawR2.addBox(-0.3F, -0.8F, -1.0F, 2, 2, 1, 0.0F);
		this.Spike18 = new AdvancedModelRenderer(this, 22, 34);
		this.Spike18.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.Spike18.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(Spike18, 0.6433283622851098F, -0.0F, 0.0F);
		this.Spike16 = new AdvancedModelRenderer(this, 34, 34);
		this.Spike16.setRotationPoint(0.0F, -0.3F, 3.8F);
		this.Spike16.addBox(-0.5F, -0.5F, -0.2F, 1, 1, 2, 0.0F);
		this.setRotateAngle(Spike16, 0.8178612874845427F, -0.0F, 0.0F);
		this.BodyLower = new AdvancedModelRenderer(this, 93, 47);
		this.BodyLower.setRotationPoint(0.0F, 10.990432633846082F, -3.327786901925256F);
		this.BodyLower.addBox(-3.5F, -1.5F, 0.0F, 7, 6, 7, 0.0F);
		this.ClawL2 = new AdvancedModelRenderer(this, 128, 52);
		this.ClawL2.mirror = true;
		this.ClawL2.setRotationPoint(2.0F, 0.0F, 0.0F);
		this.ClawL2.addBox(-0.3F, -0.8F, 0.0F, 2, 2, 1, 0.0F);
		this.Spike19 = new AdvancedModelRenderer(this, 22, 34);
		this.Spike19.setRotationPoint(0.0F, 0.0F, 6.1F);
		this.Spike19.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(Spike19, 0.668842409753383F, -0.0F, 0.0F);
		this.Spike10 = new AdvancedModelRenderer(this, 34, 34);
		this.Spike10.setRotationPoint(0.0F, -1.6F, 4.6F);
		this.Spike10.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(Spike10, 0.8178612874845427F, -0.0F, 0.0F);
		this.ThighL = new AdvancedModelRenderer(this, 0, 23);
		this.ThighL.mirror = true;
		this.ThighL.setRotationPoint(3.7F, 1.6F, 6.0F);
		this.ThighL.addBox(-1.43F, -0.5F, -2.0F, 3, 7, 4, 0.0F);
		this.setRotateAngle(ThighL, -0.19198621771937624F, -0.0F, 0.0F);
		this.Lmembrane1 = new AdvancedModelRenderer(this, 0, 78);
		this.Lmembrane1.mirror = true;
		this.Lmembrane1.setRotationPoint(-4.5F, 0.0F, 0.0F);
		this.Lmembrane1.addBox(-4.5F, 0.0F, 0.5F, 9, 0, 8, 0.0F);
		this.setRotateAngle(Lmembrane1, 0.0F, 3.141592653589793F, 0.0F);
		this.ToeR3 = new AdvancedModelRenderer(this, 1, 63);
		this.ToeR3.setRotationPoint(-1.0F, 6.1F, 0.7F);
		this.ToeR3.addBox(-0.5F, -0.8F, -4.0F, 1, 2, 5, 0.0F);
		this.setRotateAngle(ToeR3, 0.5235987755982988F, 0.0F, 0.0F);
		this.Spike5 = new AdvancedModelRenderer(this, 34, 34);
		this.Spike5.setRotationPoint(0.0F, -1.5F, 1.0F);
		this.Spike5.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(Spike5, 0.8178612874845427F, -0.0F, 0.0F);
		this.Spike6 = new AdvancedModelRenderer(this, 22, 34);
		this.Spike6.setRotationPoint(0.0F, -1.1F, 3.5F);
		this.Spike6.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 3, 0.0F);
		this.setRotateAngle(Spike6, 0.8178612874845427F, -0.0F, 0.0F);
		this.ArmR2 = new AdvancedModelRenderer(this, 132, 11);
		this.ArmR2.setRotationPoint(-9.6F, 0.0F, -0.6F);
		this.ArmR2.addBox(-12.5F, -1.0F, -1.5F, 13, 2, 3, 0.0F);
		this.setRotateAngle(ArmR2, 0.08726646259971647F, -0.8552113334772213F, 0.0F);
		this.HornL3 = new AdvancedModelRenderer(this, 36, 28);
		this.HornL3.mirror = true;
		this.HornL3.setRotationPoint(1.9F, -0.8F, -0.8F);
		this.HornL3.addBox(-0.4F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(HornL3, -0.05235987755982988F, 0.3141592653589793F, 0.0F);
		this.Spike9 = new AdvancedModelRenderer(this, 34, 34);
		this.Spike9.setRotationPoint(0.0F, -0.9F, 6.5F);
		this.Spike9.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(Spike9, 0.8178612874845427F, -0.0F, 0.0F);
		this.Jaw = new AdvancedModelRenderer(this, 34, 56);
		this.Jaw.setRotationPoint(0.5F, 0.4F, -3.3F);
		this.Jaw.addBox(-1.5F, -0.4F, -5.5F, 3, 1, 5, 0.0F);
		this.setRotateAngle(Jaw, -0.045553093477052F, -0.0F, 0.0F);
		this.FingerR3 = new AdvancedModelRenderer(this, 128, 31);
		this.FingerR3.setRotationPoint(-8.3F, 0.0F, 0.2F);
		this.FingerR3.addBox(-17.0F, -0.5F, -1.0F, 16, 1, 2, 0.0F);
		this.setRotateAngle(FingerR3, 0.0F, 1.9198621771937625F, 0.0F);
		this.Lmembrane2 = new AdvancedModelRenderer(this, 34, 74);
		this.Lmembrane2.mirror = true;
		this.Lmembrane2.setRotationPoint(-5.5F, 0.0F, 0.0F);
		this.Lmembrane2.addBox(-7.5F, -0.1F, 1.21F, 15, 0, 12, 0.0F);
		this.setRotateAngle(Lmembrane2, 0.0F, 2.530727415391778F, 0.0F);
		this.HornL = new AdvancedModelRenderer(this, 59, 34);
		this.HornL.mirror = true;
		this.HornL.setRotationPoint(1.8F, -2.5F, -1.0F);
		this.HornL.addBox(-0.4F, -0.5F, 0.0F, 1, 2, 4, 0.0F);
		this.setRotateAngle(HornL, 0.3141592653589793F, 0.33161255787892263F, 0.19198621771937624F);
		this.Tail3 = new AdvancedModelRenderer(this, 72, 21);
		this.Tail3.setRotationPoint(0.0F, -0.1F, 7.7F);
		this.Tail3.addBox(-1.5F, -1.3F, 1.0F, 3, 3, 9, 0.0F);
		this.setRotateAngle(Tail3, 0.091106186954104F, -0.0F, 0.0F);
		this.Spike17 = new AdvancedModelRenderer(this, 34, 34);
		this.Spike17.setRotationPoint(0.0F, -0.5F, 7.3F);
		this.Spike17.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 2, 0.0F);
		this.setRotateAngle(Spike17, 0.8178612874845427F, -0.0F, 0.0F);
		this.BodyLower.addChild(this.BodyUpper);
		this.Neck2.addChild(this.Neck3);
		this.ArmR2.addChild(this.FingerR1);
		this.FingerR1.addChild(this.FingerR2);
		this.ArmL2.addChild(this.FingerL1);
		this.ArmL2.addChild(this.FingerL3);
		this.Head.addChild(this.HeadFront);
		this.Tail1.addChild(this.Tail2);
		this.Tail4.addChild(this.Club);
		this.ArmR2.addChild(this.Rmembrane2);
		this.ArmR1.addChild(this.Rmembrane1);
		this.Neck1.addChild(this.Neck2);
		this.BodyLower.addChild(this.ThighR);
		this.BodyUpper.addChild(this.Neck1);
		this.ThighR.addChild(this.LegR);
		this.FingerL1.addChild(this.FingerL2);
		this.BodyUpper.addChild(this.ArmL1);
		this.ClawL2.addChild(this.ClawL3);
		this.HeadFront.addChild(this.Teeth1);
		this.Club.addChild(this.Spike20);
		this.LegL.addChild(this.ToeL2);
		this.LegR.addChild(this.ToeR2);
		this.Head.addChild(this.HornR3);
		this.HornL.addChild(this.HornL2);
		this.BodyLower.addChild(this.Tail1);
		this.Tail2.addChild(this.Spike11);
		this.FingerR4.addChild(this.Rmembrane3);
		this.LegR.addChild(this.ToeR1);
		this.ArmR2.addChild(this.FingerR4);
		this.ArmL2.addChild(this.ClawLPivot);
		this.ThighL.addChild(this.LegL);
		this.BodyUpper.addChild(this.Spike7);
		this.ClawR2.addChild(this.ClawR3);
		this.Neck2.addChild(this.Spike2);
		this.Neck2.addChild(this.Spike3);
		this.Tail2.addChild(this.Spike12);
		this.Tail3.addChild(this.Tail4);
		this.HeadFront.addChild(this.Teeth2);
		this.Neck3.addChild(this.Head);
		this.FingerL1.addChild(this.Lmembrane4);
		this.HornR.addChild(this.HornR2);
		this.FingerR1.addChild(this.Rmembrane4);
		this.BodyLower.addChild(this.Spike8);
		this.Tail3.addChild(this.Spike15);
		this.LegL.addChild(this.ToeL3);
		this.Neck3.addChild(this.Spike1);
		this.LegL.addChild(this.ToeL1);
		this.Tail3.addChild(this.Spike13);
		this.ArmL2.addChild(this.FingerL4);
		this.FingerL4.addChild(this.Lmembrane3);
		this.ArmR2.addChild(this.ClawRPivot);
		this.Tail3.addChild(this.Spike14);
		this.Head.addChild(this.HornR);
		this.Neck1.addChild(this.Spike4);
		this.ArmL1.addChild(this.ArmL2);
		this.BodyUpper.addChild(this.ArmR1);
		this.ClawR.addChild(this.ClawR2);
		this.ClawRPivot.addChild(this.ClawR);
		this.Club.addChild(this.Spike18);
		this.Tail4.addChild(this.Spike16);
		this.ClawLPivot.addChild(this.ClawL);
		this.ClawL.addChild(this.ClawL2);
		this.Club.addChild(this.Spike19);
		this.Tail1.addChild(this.Spike10);
		this.BodyLower.addChild(this.ThighL);
		this.ArmL1.addChild(this.Lmembrane1);
		this.LegR.addChild(this.ToeR3);
		this.BodyUpper.addChild(this.Spike5);
		this.BodyUpper.addChild(this.Spike6);
		this.ArmR1.addChild(this.ArmR2);
		this.Head.addChild(this.HornL3);
		this.BodyLower.addChild(this.Spike9);
		this.Head.addChild(this.Jaw);
		this.ArmR2.addChild(this.FingerR3);
		this.ArmL2.addChild(this.Lmembrane2);
		this.Head.addChild(this.HornL);
		this.Tail2.addChild(this.Tail3);
		this.Tail4.addChild(this.Spike17);
		animator = ModelAnimator.create();
		this.updateDefaultPose();
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		animate((IAnimatedEntity) entity, f, f1, f2, f3, f4, f5);
		ModelUtils.renderAll(boxList);
	}

	public void animate(IAnimatedEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.resetToDefaultPose();
		animator.update(entity);
		setRotationAngles(f, f1, f2, f3, f4, f5, (Entity) entity);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		float speed_walk = 0.2F;
		float speed_idle = 0.05F;
		float degree_walk = 0.5F;
		float degree_idle = 0.5F;
		AdvancedModelRenderer[] NECK = new AdvancedModelRenderer[] { Neck2, Neck3, Head };
		AdvancedModelRenderer[] TAIL = new AdvancedModelRenderer[] { Tail1, Tail2, Tail3, Tail4, Club };
		this.walk(ThighR, speed_walk, degree_walk * 1.2F, true, 0, 0F, f, f1);
		this.walk(LegR, speed_walk, degree_walk * 0.7F, true, 0, -0.2F, f, f1);
		this.walk(ToeR1, -speed_walk, degree_walk * 0.7F, false, -0.7F, 0F, f, f1);
		this.walk(ToeR2, -speed_walk, degree_walk * 0.7F, false, -0.7F, 0F, f, f1);
		this.walk(ToeR3, -speed_walk, degree_walk * 0.7F, false, -0.7F, 0F, f, f1);
		this.walk(ThighL, speed_walk, degree_walk * 1.2F, false, 0, 0F, f, f1);
		this.walk(LegL, speed_walk, degree_walk * 0.7F, false, 0, -0.2F, f, f1);
		this.walk(ToeL1, -speed_walk, degree_walk * 0.7F, false, -0.7F, 0F, f, f1);
		this.walk(ToeL2, -speed_walk, degree_walk * 0.7F, false, -0.7F, 0F, f, f1);
		this.walk(ToeL3, -speed_walk, degree_walk * 0.7F, false, -0.7F, 0F, f, f1);
		this.bob(BodyLower, speed_walk * 2, degree_walk * 1.7F, false, f, f1);
		this.bob(ThighR, speed_walk, degree_walk * 1.7F, false, f, f1);
		this.bob(ThighL, speed_walk, degree_walk * 1.7F, false, f, f1);
		this.chainSwing(TAIL, speed_walk, degree_walk * 0.25F, -2, f, f1);
		this.chainWave(TAIL, speed_walk, degree_walk * 0.15F, 2, f, f1);
		this.chainSwing(NECK, speed_walk, degree_walk * 0.15F, 2, f, f1);
		this.chainWave(NECK, speed_walk, degree_walk * 0.05F, -2, f, f1);
		this.swing(ArmR1, speed_walk, degree_walk * 1.2F, true, 0, 0F, f, f1);
		this.swing(ArmL1, speed_walk, degree_walk * 1.2F, true, 0, 0F, f, f1);
		this.flap(ClawL, speed_walk, degree_walk * 1.2F, false, 0, 0.5F, f, f1);
		this.flap(ClawR, speed_walk, degree_walk * 1.2F, true, 0, -0.5F, f, f1);
		this.bob(BodyLower, speed_idle, degree_idle * 1.3F, false, entity.ticksExisted, 1);
		this.bob(ThighR, speed_idle, -degree_idle * 1.3F, false, entity.ticksExisted, 1);
		this.bob(ThighL, speed_idle, -degree_idle * 1.3F, false, entity.ticksExisted, 1);
		this.bob(ArmL1, speed_idle, -degree_idle * 1.3F, false, entity.ticksExisted, 1);
		this.bob(ArmR1, speed_idle, -degree_idle * 1.3F, false, entity.ticksExisted, 1);
		this.chainSwing(TAIL, speed_idle, degree_idle * 0.25F, -2, entity.ticksExisted, 1);
		this.chainWave(TAIL, speed_idle, degree_idle * 0.15F, -2, entity.ticksExisted, 1);
		this.chainWave(NECK, speed_idle, degree_idle * 0.15F, -3, entity.ticksExisted, 1);
		this.walk(Neck1, speed_idle, degree_idle * 0.15F, false, 0, 0, entity.ticksExisted, 1);
	}
}
