package dao;

import java.util.List;

import entities.Part;

public interface PartDao {

	public void insert(Part part);
	public void update(Part part);
	public void remove(Integer id);
	public Part findApart(Integer id);
	public List<Part> findAll();
}
