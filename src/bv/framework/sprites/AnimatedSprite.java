package bv.framework.sprites;

import java.awt.Color;
import java.util.ArrayList;

import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.state.Tickable;

@SuppressWarnings("serial")
public class AnimatedSprite extends ArrayList<Sprite> implements Cloneable, Tickable, Renderable {

	public int index = 0;
	public double scale = 1;
	public double heading = 0;
	public Color color = Color.MAGENTA;
	
	public AnimatedSprite(Sprite...frames) {
		for (Sprite sf:frames) {
			this.add(sf.clone());
		}
	}
	public AnimatedSprite(AnimatedSprite template) {
		this(template.toArray(new Sprite[]{}));
		this.index = template.index;
		this.scale = template.scale;
		this.heading = template.heading;
		this.color = template.color;
	}
	public AnimatedSprite clone() {
		return new AnimatedSprite(this);
	}
	
	public void tick() {
		index++;
	}
	public Sprite currentFrame() {
		return this.get(index % this.size());
	}
	
	public void render(Renderer r, CVector position, double scale, double heading, Color color) {
		this.currentFrame().render(r, position, scale * this.scale, heading + this.heading, color);
	}
	public void render(Renderer r, CVector position, double scale, double heading) {
		this.render(r, position, scale, heading, this.color);
	}
	public void render(Renderer r, CVector position, double heading, Color color) {
		this.render(r, position, 1.0, heading, color);
	}
	public void render(Renderer r, CVector position, double heading) {
		this.render(r, position, 1.0, heading, this.color);
	}
	public void render(Renderer r, CVector position, Color color) {
		this.render(r, position, 1.0, 0.0, color);
	}
	public void render(Renderer r, CVector position) {
		this.render(r, position, 1.0, 0.0, this.color);
	}
	public void render(Renderer r) {
		this.render(r, new CVector(0,0), 1.0, 0.0, this.color);
	}
	
	public void add(Sprite...frames) {
		for (Sprite f : frames) super.add(f);
	}
	// commented out even though it makes sense
	// bascially, I'm tired and don't want to deal with this crap rn
//	public Sprite get(int index) {
//		Sprite result = super.get(index).clone();
//		result.scale *= this.scale;
//		result.heading += this.heading;
//		return result;
//	}
//	public Sprite getRaw(int index) {
//		return super.get(index);
//	}
	
	public AnimatedSprite scale(double factor) {
		this.scale = factor;
		return this;
	}
	public AnimatedSprite scaleNew(double factor) {
		return this.clone().scale(factor);
	}
	
	public Rect rectBounds() {
		return this.polyBounds().rectBounds();
	}
	public Poly polyBounds() {
		return this.currentFrame().polyBounds();
	}

}
