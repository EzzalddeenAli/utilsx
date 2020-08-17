package org.kingtec.utils.tags;

import org.kingtec.utils.OnObjectClickListener;
import org.kingtec.utils.OnObjectClickListenerView;

/**
 * Created by 500 on 03/04/2020.
 */


public class TagSuperBulder {

    public SubNameBulder name(String o) {
        TagSuper tagSuper = new TagSuper();
        tagSuper.sname(o);
        return new SubNameBulder(tagSuper);
    }


    public class SubNameBulder {
        TagSuper a;

        public SubNameBulder(TagSuper tagSuperBulder) {
            this.a = tagSuperBulder;
        }

        public IconBulder subName(String o) {
            a.subName(o);
            return new IconBulder(a);
        }
    }

    public class IconBulder {
        TagSuper a;

        public IconBulder(TagSuper tagSuperBulder) {
            this.a = tagSuperBulder;
        }

        public DescBulder icon(int o) {
            a.image(o);
            return new DescBulder(a);
        }
    }

    public class DescBulder {
        TagSuper a;

        public DescBulder(TagSuper tagSuperBulder) {
            this.a = tagSuperBulder;
        }

        public CkickBulder desc(String o) {
            a.desc(o);
            return new CkickBulder(a);
        }
    }

    public class CkickBulder {
        TagSuper a;

        public CkickBulder(TagSuper tagSuperBulder) {
            this.a = tagSuperBulder;
        }

        public TagSuper click(OnObjectClickListener o) {

            return a.click(o);
        }

        public TagSuper clickV(OnObjectClickListenerView o) {

            return a.click(o);
        }
    }

}