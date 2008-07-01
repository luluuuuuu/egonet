package com.endlessloopsoftware.ego.client.graph;

import java.awt.*;

public class NodeProperty extends GraphProperty {

	public static enum NodeShape {
		Circle, Square, Pentagon, Hexagon, Triangle, Star, RoundedRectangle
	}
	
	public static enum Property {
		Color, Shape, Size, Label
	}
	public NodeProperty(String label, Color color, NodeShape shape, int size) {
		this.label = label;
		this.color = color;
		this.shape = shape;
		this.size = size;
	}

	private NodeShape shape;

	private String label;

	private boolean showLabel = false;

	private Property property = null;
	
	private String toolTipText = "";
	
	public String getToolTipText() {
		return toolTipText;
	}

	public void setToolTipText(String toolTipText) {
		this.toolTipText = toolTipText;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public NodeProperty() {
		super();
		this.shape = NodeShape.Circle;
	}

	public NodeShape getShape() {
		return this.shape;
	}

	public void setShape(NodeShape shape) {
		this.shape = shape;
	}
	
	public void setShapeFromString(String nodeShape){
		if(nodeShape.equalsIgnoreCase("Circle")){
			this.shape = NodeShape.Circle;
		}else if(nodeShape.equalsIgnoreCase("Square")){
			this.shape = NodeShape.Square;
		}else if(nodeShape.equalsIgnoreCase("Pentagon")){
			this.shape = NodeShape.Pentagon;
		}else if(nodeShape.equalsIgnoreCase("Hexagon")){
			this.shape = NodeShape.Hexagon;
		}else if(nodeShape.equalsIgnoreCase("Triangle")){
			this.shape = NodeShape.Triangle;
		}else if (nodeShape.equalsIgnoreCase("Star")){
			this.shape = NodeShape.Star;
		}else {
			this.shape = NodeShape.RoundedRectangle;
		}
	}

	public String toString() {
		String str;
		str = this.shape.toString() + " " + this.color.toString() + " "
				+ this.size + " " + this.label + " "  + this.property;
		return str;
	}

	public boolean isShowLabel() {
		return showLabel;
	}

	public void setShowLabel(boolean showLabel) {
		this.showLabel = showLabel;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	
	
}