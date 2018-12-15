package androidx.databinding;

public class DataBinderMapperImpl extends MergedDataBinderMapper {
  DataBinderMapperImpl() {
    addMapper(new fr.esiea.mymovie.DataBinderMapperImpl());
  }
}
