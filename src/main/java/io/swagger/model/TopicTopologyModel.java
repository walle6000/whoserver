package io.swagger.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
/**
 * TopicTopology
 */
@SuppressWarnings("serial")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-05T03:12:51.178Z")
public class TopicTopologyModel implements Serializable  {

	
   @JsonProperty("nodes")
   private List<Node> nodes;
   
   @JsonProperty("links")
   private List<Link> links;
   
   public TopicTopologyModel() {
		super();
		this.nodes = new ArrayList<Node>();
		this.links = new ArrayList<Link>();
	}
	
   public TopicTopologyModel(String rootName,String rootSrc) {
		super();
		this.nodes = new ArrayList<Node>();
		this.links = new ArrayList<Link>();
		addNode(new Node(rootName,rootSrc));
		addLink(new Link(rootName,rootName,"0"));
	}

   public TopicTopologyModel(List<Node> nodes, List<Link> links) {
	super();
	this.nodes = nodes;
	this.links = links;
  }



public static class Node implements Serializable{
	   
	   @JsonProperty("name")
	   private String name;
	   
	   @JsonProperty("src")
	   private String src;

	   @ApiModelProperty(value = "")
	   public String getName() {
		return name;
	   }

	   public void setName(String name) {
		this.name = name;
	   }

	   @ApiModelProperty(value = "")
	   public String getSrc() {
		return src;
	   }

	   public void setSrc(String src) {
		this.src = src;
	   }

	   
	   
	public Node() {
		super();
	}

	public Node(String name, String src) {
		super();
		this.name = name;
		this.src = src;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	   
	   
	   
   }
 
   public static class Link implements Serializable{
	   
	   @JsonProperty("source")
	   private String source;
	   
	   @JsonProperty("target")
	   private String target;
	   
	   @JsonProperty("relation")
	   private String relation;
	   
	   

	   public Link() {
		super();
	}

	public Link(String source, String target, String relation) {
		super();
		this.source = source;
		this.target = target;
		this.relation = relation;
	}

	public String getSource() {
		return source;
	   }

	   public void setSource(String source) {
		this.source = source;
	   }

	   public String getTarget() {
		return target;
	   }

	   public void setTarget(String target) {
		this.target = target;
	   }

	   public String getRelation() {
		return relation;
	   }

	   public void setRelation(String relation) {
		this.relation = relation;
	   }
   }
   
   

   @ApiModelProperty(value = "")
	public List<Node> getNodes() {
		return nodes;
	}
	
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	
	@ApiModelProperty(value = "")
	public List<Link> getLinks() {
		return links;
	}
	
	public void setLinks(List<Link> links) {
		this.links = links;
	}
   
	
	public void addNode(Node node){
		this.nodes.add(node);
	}
	
	public void addLink(Link link){
		this.links.add(link);
	}
  
}

