package bv.sportsGame.game.entities;

import bv.gameFramework.core.Core;
import bv.gameFramework.graphics.Renderable;
import bv.gameFramework.graphics.Renderer;
import bv.gameFramework.physics.Collidable;
import bv.gameFramework.physics.Entity;
import bv.gameFramework.spritesCore.RSprite;
import bv.math.PVector;
import bv.math.Poly;
import bv.math.Rect;
/**
 * An abstract class for all projectiles to extend
 * 
 * @author Aroosh Kumar
 * @since Monday, February 19, 2018
 * .
 */
public abstract class Projectile extends Entity implements Renderable, Collidable {
	public RSprite projectileSprite;
	public Collidable parent;
	public Projectile(RSprite sprite, double speed, Collidable parent){
		this.projectileSprite = sprite;
		this.velocity.setMagnitude(speed);
		this.velocity.setAngle(sprite.heading);
		this.position = sprite.position;
		this.parent = parent;
	}
	public void updatePhysics() {
		super.updatePhysics();
	}
	public void render(Renderer r){
		projectileSprite.render(r);
	}
	@Override
	public Rect rectBounds() {
		return projectileSprite.rectBounds();
	}

	@Override
	public Poly polyBounds() {
		return projectileSprite.polyBounds();
	}
	@Override
	public void onCollision(PVector newVelocity, Entity object) {
		if ((!(((Collidable)object) == this.parent)) && !(object instanceof Projectile)){
			velocity = new PVector(newVelocity);
			Core.state().objects.remove(Core.state().objects.indexOf(this));
		}
	}
}
