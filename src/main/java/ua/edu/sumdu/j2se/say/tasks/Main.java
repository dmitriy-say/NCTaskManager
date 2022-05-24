package ua.edu.sumdu.j2se.say.tasks;

public class Main {
	public static void main(String[] args) {
		System.out.println("Hello");
		Task a = new Task("A",0);
		Task b = new Task("B",10);
		Task c = new Task("C",20);
		Task d = new Task("D",50);
		Task e = new Task("E",70);
		Task f = new Task("F",90);
		Task g = new Task("G",100);
		Task h = new Task("H",110);
		Task i = new Task("I",200);
		Task j = new Task("J",500);
		Task k = new Task("K",1000);
		Task l = new Task("L", 0, 0,0);
		Task m = new Task("M", 1, 0,0);
		Task n = new Task("N", 0, 1,0);
		Task o = new Task("O", 0, 0,1);
		Task p = new Task("P", 0, 10,0);
		Task q = new Task("Q", 0, 10,10);
		Task r = new Task("R", 0, 10,1);
		Task s = new Task("S", 0, 100,10);
		Task t = new Task("T", 10, 1000,100);

		ArrayTaskList list = new ArrayTaskList();
		list.add(a);
		list.add(b);
		list.add(c);
		list.add(d);
		list.add(e);
		list.add(f);
		list.add(g);
		list.add(h);
		list.add(i);
		list.add(j);
		list.add(k);
		list.add(l);
		list.add(m);
		list.add(n);
		list.add(o);
		list.add(p);
		list.add(q);
		list.add(r);
		list.add(s);
		list.add(t);

		a.setActive(true);
		b.setActive(true);
		c.setActive(true);
		d.setActive(true);
		e.setActive(true);
		f.setActive(true);
		g.setActive(true);
		h.setActive(true);
		i.setActive(true);
		j.setActive(true);
		k.setActive(true);
		l.setActive(true);
		m.setActive(true);
		n.setActive(true);
		o.setActive(true);
		p.setActive(true);
		q.setActive(true);
		r.setActive(true);
		s.setActive(true);
		t.setActive(true);

		list.remove(t);
		list.remove(s);
		list.remove(r);
		list.remove(q);
		list.remove(p);
		list.remove(o);
		list.remove(n);
		list.remove(m);
		list.remove(l);
		list.remove(k);
		list.remove(j);
		list.remove(i);

		System.out.println("list.size:" + list.size());
		System.out.println("list.length:" + list.sizeAll());
		System.out.println(a.nextTimeAfter(1));
		System.out.println(list.incoming(10, 100).size());
	}
}
