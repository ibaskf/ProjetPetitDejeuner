package fr.treeptik.editor;

import java.beans.PropertyEditorSupport;

import org.springframework.util.StringUtils;

import fr.treeptik.model.TypeDej;

@SuppressWarnings("unchecked")
public class TypeDejEditor extends PropertyEditorSupport {
 private Class clazz;

 public TypeDejEditor(Class clazz) {
  this.clazz = clazz;
 };

 public String getAsText() {
	 if(getValue() == null)
		 return "";
	 
	 System.out.println("get as text"+ getValue());
	 
  return ((TypeDej) getValue()).name();
 
 }

 public void setAsText(String text) throws IllegalArgumentException {
	 if(text==null)
		 return;
	 if(text.equals("Aucune")){
		 System.out.println("set as text"+ TypeDej.valueOf(text));
		 setValue(TypeDej.valueOf(text));
	 }
	 if(text.equals("Sale")){
		 System.out.println("set as text"+ TypeDej.valueOf(text));
		 setValue(TypeDej.valueOf(text));
	 }
	 if(text.equals("Sucre")){
		 System.out.println("set as text"+ TypeDej.valueOf(text));
		 setValue(TypeDej.valueOf(text));
	 }
	 
 
 }

}