package ua.edu.sumdu.j2se.say.tasks;

import static ua.edu.sumdu.j2se.say.tasks.ListTypes.types.ARRAY;
import static ua.edu.sumdu.j2se.say.tasks.ListTypes.types.LINKED;
import static ua.edu.sumdu.j2se.say.tasks.TaskListFactory.createTaskList;

public class Main {
	public static void main(String[] args) throws CloneNotSupportedException {
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
		Task l = new Task("L", 0, 0,1);
		Task m = new Task("M", 0, 1,1);
		Task n = new Task("N", 0, 1,1);
		Task o = new Task("O", 0, 0,1);
		Task p = new Task("P", 0, 10,1);
		Task q = new Task("Q", 0, 10,10);
		Task r = new Task("R", 0, 10,1);
		Task s = new Task("S", 0, 100,10);
		Task t = new Task("T", 10, 1000,100);

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

		System.out.println("a: " + a);
		System.out.println("a.nextTimeAfter(10): " + a.nextTimeAfter(10));
		System.out.println("s.nextTimeAfter(10): " + s.nextTimeAfter(10));

		AbstractTaskList al = createTaskList(ARRAY);
		AbstractTaskList ll = createTaskList(LINKED);

		al.add(a);
		al.add(b);
		al.add(c);
		al.add(d);
		al.add(e);
		al.add(f);
		al.add(g);
		al.add(h);
		al.add(i);
		al.add(j);
		al.add(k);
		al.add(l);
		al.add(m);
		al.add(n);
		al.add(o);
		al.add(p);
		al.add(q);
		al.add(r);
		al.add(s);
		al.add(t);



		al.remove(t);
		al.remove(s);
		al.remove(r);
		al.remove(q);
		al.remove(p);
		al.remove(o);
		al.remove(n);
		al.remove(m);
		al.remove(l);
		al.remove(k);
		al.remove(j);
		al.remove(i);

		System.out.println("al: " + al);
		System.out.println("al.incoming(10, 100)" + al.incoming(10, 100));
		System.out.println("al size: " + al.size());
		System.out.println("al length: " + al.thisArraySize());

		ll.add(a);
		ll.add(b);
		ll.add(c);
		ll.add(d);
		ll.add(e);
		ll.add(f);
		ll.add(g);
		ll.add(h);
		ll.add(i);
		ll.add(j);
		ll.add(k);
		ll.add(l);
		ll.add(m);
		ll.add(n);
		ll.add(o);
		ll.add(p);
		ll.add(q);
		ll.add(r);
		ll.add(s);
		ll.add(t);


		System.out.println("ll: " + ll);
		System.out.println("al.incoming(10, 100)" + al.incoming(10, 100));
		System.out.println("ll.size: " + ll.size());


		for (Task abc : al
			 ) {
			System.out.println("abc: " + abc.toString());
		}

		AbstractTaskList al1 = al.clone();
		System.out.println("al1: " + al1.toString());

		AbstractTaskList ll1 = ll.clone();
		System.out.println("ll1: " + ll1.toString());


		Task zzz = a.clone();
		System.out.println("zzz: " + zzz.toString());

		System.out.println("a.hashcode: " + a.hashCode());
		System.out.println("al.hashcode: " + al.hashCode());



		System.out.println(al.getClass());
		System.out.println(ll.getClass());

		System.out.println(al instanceof AbstractTaskList);
		System.out.println(ll instanceof AbstractTaskList);

		Object obj = new ArrayTaskList();

		ArrayTaskList atl1 = new ArrayTaskList();

	System.out.println(al.equals(atl1));
	System.out.println(al1.equals(ll));
	System.out.println(atl1.getClass());
	System.out.println(obj.getClass());

	}
}
